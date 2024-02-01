package com.zzaug.member.domain.usecase.member;

import com.zzaug.member.domain.dto.member.LoginUseCaseRequest;
import com.zzaug.member.domain.dto.member.MemberAuthToken;
import com.zzaug.member.domain.event.LoginEvent;
import com.zzaug.member.domain.exception.DBSource;
import com.zzaug.member.domain.exception.PasswordNotMatchException;
import com.zzaug.member.domain.exception.SourceNotFoundException;
import com.zzaug.member.domain.external.dao.member.AuthenticationDao;
import com.zzaug.member.domain.external.dao.member.ExternalContactDao;
import com.zzaug.member.domain.external.service.log.LoginLogCommand;
import com.zzaug.member.domain.model.member.MemberAuthentication;
import com.zzaug.member.domain.model.member.MemberContacts;
import com.zzaug.member.domain.support.entity.MemberAuthenticationConverter;
import com.zzaug.member.domain.support.entity.MemberContactExtractor;
import com.zzaug.member.entity.member.AuthenticationEntity;
import com.zzaug.member.entity.member.CertificationData;
import com.zzaug.member.entity.member.ExternalContactEntity;
import com.zzaug.member.entity.member.PasswordData;
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
	private final ExternalContactDao externalContactDao;

	private final LoginLogCommand loginLogCommand;

	private final PasswordEncoder passwordEncoder;

	private final TokenGenerator tokenGenerator;

	private final ApplicationEventPublisher applicationEventPublisher;

	public MemberAuthToken execute(LoginUseCaseRequest request) {
		final CertificationData certification =
				CertificationData.builder().certification(request.getCertification()).build();
		final PasswordData password = PasswordData.builder().password(request.getPassword()).build();
		final String userAgent = request.getUserAgent();

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

		List<ExternalContactEntity> contacts =
				externalContactDao.findAllByMemberIdAndDeletedFalse(memberAuthentication.getMemberId());
		MemberContacts memberContacts = MemberContactExtractor.execute(contacts);

		AuthToken authToken =
				tokenGenerator.generateAuthToken(
						memberAuthentication.getMemberId(),
						List.of(Roles.ROLE_USER),
						memberAuthentication.getCertification(),
						memberContacts.getEmail(),
						memberContacts.getGithub());

		loginLogCommand.saveLoginLog(memberAuthentication.getMemberId(), userAgent);

		publishEvent(memberAuthentication);

		return MemberAuthToken.builder()
				.accessToken(authToken.getAccessToken())
				.refreshToken(authToken.getRefreshToken())
				.build();
	}

	private void publishEvent(MemberAuthentication memberAuthentication) {
		applicationEventPublisher.publishEvent(
				LoginEvent.builder().memberId(memberAuthentication.getMemberId()).build());
	}
}
