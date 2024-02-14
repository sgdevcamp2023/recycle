package com.zzaug.member.domain.usecase.member;

import com.zzaug.member.domain.dto.member.LoginUseCaseRequest;
import com.zzaug.member.domain.dto.member.MemberAuthToken;
import com.zzaug.member.domain.event.LoginEvent;
import com.zzaug.member.domain.exception.DBSource;
import com.zzaug.member.domain.exception.PasswordNotMatchException;
import com.zzaug.member.domain.exception.SourceNotFoundException;
import com.zzaug.member.domain.external.dao.member.AuthenticationDao;
import com.zzaug.member.domain.external.security.auth.EnrollTokenCacheService;
import com.zzaug.member.domain.external.service.log.LoginLogCommand;
import com.zzaug.member.domain.external.service.member.MemberContactsQuery;
import com.zzaug.member.domain.model.member.MemberAuthentication;
import com.zzaug.member.domain.model.member.MemberContacts;
import com.zzaug.member.domain.support.entity.MemberAuthenticationConverter;
import com.zzaug.member.entity.member.AuthenticationEntity;
import com.zzaug.member.entity.member.CertificationData;
import com.zzaug.member.entity.member.PasswordData;
import com.zzaug.member.persistence.support.transaction.UseCaseTransactional;
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
public class LoginUseCase {

	private final AuthenticationDao authenticationDao;

	private final MemberContactsQuery memberContactsQuery;
	private final LoginLogCommand loginLogCommand;

	private final PasswordEncoder passwordEncoder;

	private final TokenGenerator tokenGenerator;

	private final ApplicationEventPublisher applicationEventPublisher;

	// security
	private final EnrollTokenCacheService enrollWhiteAccessTokenCacheServiceImpl;

	@UseCaseTransactional
	public MemberAuthToken execute(LoginUseCaseRequest request) {
		final CertificationData certification =
				CertificationData.builder().certification(request.getCertification()).build();
		final PasswordData password = PasswordData.builder().password(request.getPassword()).build();
		final String userAgent = request.getUserAgent();

		log.debug("Get member authentication. certification: {}", certification.getCertification());
		Optional<AuthenticationEntity> authenticationSource =
				authenticationDao.findByCertificationAndDeletedFalse(certification);
		if (authenticationSource.isEmpty()) {
			throw new SourceNotFoundException(
					DBSource.AUTHENTICATION, "Certification", certification.getCertification());
		}
		MemberAuthentication memberAuthentication =
				MemberAuthenticationConverter.from(authenticationSource.get());

		if (!memberAuthentication.isMatchPassword(passwordEncoder, password.getPassword())) {
			throw new PasswordNotMatchException();
		}

		MemberContacts memberContacts = memberContactsQuery.execute(memberAuthentication);

		AuthToken authToken =
				tokenGenerator.generateAuthToken(
						memberAuthentication.getMemberId(),
						List.of(Roles.ROLE_USER),
						memberAuthentication.getCertification(),
						memberContacts.getEmail(),
						memberContacts.getGithub());

		log.debug("Save login log. memberId: {}", memberAuthentication.getMemberId());
		loginLogCommand.saveLoginLog(memberAuthentication.getMemberId(), userAgent);

		// check duplication
		enrollWhiteAccessTokenCacheServiceImpl.execute(authToken.getAccessToken());

		publishEvent(memberAuthentication);

		return MemberAuthToken.builder()
				.memberId(memberAuthentication.getMemberId())
				.accessToken(authToken.getAccessToken())
				.refreshToken(authToken.getRefreshToken())
				.build();
	}

	private void publishEvent(MemberAuthentication memberAuthentication) {
		// todo listener에서 해당 이벤트를 rabbitmq로 publish하여야 한다.
		log.debug("Publish login event. memberId: {}", memberAuthentication.getMemberId());
		applicationEventPublisher.publishEvent(
				LoginEvent.builder().memberId(memberAuthentication.getMemberId()).build());
	}
}
