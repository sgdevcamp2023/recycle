package com.zzaug.member.domain.usecase.member;

import com.zzaug.member.domain.dto.member.UpdateMemberUseCaseRequest;
import com.zzaug.member.domain.dto.member.UpdateMemberUseCaseResponse;
import com.zzaug.member.domain.event.UpdateMemberEvent;
import com.zzaug.member.domain.exception.DBSource;
import com.zzaug.member.domain.exception.ExistSourceException;
import com.zzaug.member.domain.exception.PasswordNotMatchException;
import com.zzaug.member.domain.exception.SourceNotFoundException;
import com.zzaug.member.domain.external.dao.member.AuthenticationDao;
import com.zzaug.member.domain.external.security.AuthTokenValidator;
import com.zzaug.member.domain.external.security.auth.BlackTokenAuthCommand;
import com.zzaug.member.domain.external.security.auth.EnrollTokenCacheService;
import com.zzaug.member.domain.external.security.auth.ReplaceTokenCacheService;
import com.zzaug.member.domain.external.service.member.MemberContactsQuery;
import com.zzaug.member.domain.external.service.member.MemberSourceQuery;
import com.zzaug.member.domain.model.member.MemberAuthentication;
import com.zzaug.member.domain.model.member.MemberContacts;
import com.zzaug.member.domain.model.member.MemberSource;
import com.zzaug.member.domain.support.entity.MemberAuthenticationConverter;
import com.zzaug.member.entity.member.AuthenticationEntity;
import com.zzaug.member.entity.member.CertificationData;
import com.zzaug.member.persistence.support.transaction.MemberSecurityChainedTransactional;
import com.zzaug.security.authentication.authority.Roles;
import com.zzaug.security.token.AuthToken;
import com.zzaug.security.token.TokenGenerator;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateMemberUseCase {

	private final AuthenticationDao authenticationDao;

	private final MemberSourceQuery memberSourceQuery;
	private final MemberContactsQuery memberContactsQuery;

	// security
	private final TokenGenerator tokenGenerator;
	private final PasswordEncoder passwordEncoder;
	private final AuthTokenValidator authTokenValidator;
	private final BlackTokenAuthCommand blackTokenAuthCommand;
	private final EnrollTokenCacheService enrollBlackTokenCacheServiceImpl;
	private final ReplaceTokenCacheService replaceWhiteAccessTokenCacheServiceImpl;

	private final ApplicationEventPublisher applicationEventPublisher;

	@MemberSecurityChainedTransactional
	public UpdateMemberUseCaseResponse execute(UpdateMemberUseCaseRequest request) {
		final Long memberId = request.getMemberId();
		final CertificationData certification =
				CertificationData.builder().certification(request.getCertification()).build();
		final String password = request.getPassword();
		final String accessToken = request.getAccessToken();
		final String refreshToken = request.getRefreshToken();

		authTokenValidator.execute(refreshToken, accessToken, memberId);

		MemberSource memberSource = memberSourceQuery.execute(memberId);

		log.debug("Get authentication. memberId: {}", memberId);
		Optional<AuthenticationEntity> authenticationSource =
				authenticationDao.findByMemberIdAndDeletedFalse(memberSource.getId());
		if (authenticationSource.isEmpty()) {
			throw new SourceNotFoundException(DBSource.AUTHENTICATION, "MemberId", memberId);
		}
		MemberAuthentication memberAuthentication =
				MemberAuthenticationConverter.from(authenticationSource.get());

		if (!memberAuthentication.isMatchPassword(passwordEncoder, password)) {
			throw new PasswordNotMatchException();
		}

		if (!memberAuthentication.isSameCertification(certification.getCertification())) {
			// todo false이면 certification을 기준으로 락을 걸어 처리 해야 함
			log.debug(
					"Check duplicate certification. certification: {}", certification.getCertification());
			boolean isDuplicateId = authenticationDao.existsByCertificationAndDeletedFalse(certification);
			if (isDuplicateId) {
				throw new ExistSourceException(DBSource.AUTHENTICATION, certification.getCertification());
			}
			String originCertificationValue = memberAuthentication.getCertification();
			memberAuthentication.updateCertification(certification.getCertification());
			log.debug(
					"Update certification. memberId: {}, originCertification: {}, newCertification: {}",
					memberId,
					originCertificationValue,
					certification.getCertification());
		}

		AuthenticationEntity editAuthentication =
				MemberAuthenticationConverter.to(memberAuthentication);
		AuthenticationEntity authenticationEntity =
				authenticationDao.saveAuthentication(editAuthentication);
		log.debug(
				"Update authentication. memberId: {}, authenticationId: {}",
				memberId,
				authenticationEntity.getId());

		MemberContacts memberContacts = memberContactsQuery.execute(memberAuthentication);

		AuthToken authToken =
				tokenGenerator.generateAuthToken(
						memberSource.getId(),
						List.of(Roles.ROLE_USER),
						memberAuthentication.getCertification(),
						memberContacts.getEmail(),
						memberContacts.getGithub());

		blackTokenAuthCommand.execute(accessToken, refreshToken);
		enrollBlackTokenCacheServiceImpl.execute(accessToken, refreshToken);
		replaceWhiteAccessTokenCacheServiceImpl.execute(
				accessToken, authToken.getAccessToken(), memberAuthentication.getMemberId());

		publishEvent(memberId, memberAuthentication);

		return UpdateMemberUseCaseResponse.builder()
				.accessToken(authToken.getAccessToken())
				.refreshToken(authToken.getRefreshToken())
				.build();
	}

	private void publishEvent(Long memberId, MemberAuthentication memberAuthentication) {
		log.debug("Publish update member event. memberId: {}", memberId);
		applicationEventPublisher.publishEvent(
				UpdateMemberEvent.builder()
						.memberId(memberId)
						.memberCertification(memberAuthentication.getCertification())
						.build());
	}
}
