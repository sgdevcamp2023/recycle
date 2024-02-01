package com.zzaug.member.domain.usecase.config.mock.repository;

import com.zzaug.member.domain.external.dao.member.AuthenticationDao;
import com.zzaug.member.entity.member.AuthenticationEntity;
import com.zzaug.member.entity.member.CertificationData;
import com.zzaug.member.entity.member.PasswordData;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 테스트용 인증 DAO
 *
 * <p>요청한 인증 정보가 존재하는지 확인합니다.
 *
 * <p>프로파일이 exist-certification인 경우 이미 인증 정보가 존재합니다.
 *
 * <p>프로파일이 exist-certification이 아닌 경우 이미 인증 정보가 존재하지 않습니다.
 */
@Profile("usecase-test")
@TestComponent
@RequiredArgsConstructor
public class UMockAuthenticationDao implements AuthenticationDao, ApplicationContextAware {

	public static final Long AUTHENTICATION_ID = 1L;
	public static final Long MEMBER_ID = 1L;
	public static final String CERTIFICATION = "sample";
	public static final String PASSWORD_SOURCE = "123@abc";

	private List<String> activeProfiles;

	private final PasswordEncoder passwordEncoder;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		activeProfiles = List.of(applicationContext.getEnvironment().getActiveProfiles());
	}

	@Override
	public boolean existsByCertificationAndDeletedFalse(CertificationData certification) {
		if (activeProfiles.contains("exist-certification")) {
			return true;
		}
		return false;
	}

	@Override
	public Optional<AuthenticationEntity> findByMemberIdAndDeletedFalse(Long memberId) {
		return Optional.of(
				AuthenticationEntity.builder()
						.id(AUTHENTICATION_ID)
						.memberId(memberId)
						.certification(CertificationData.builder().certification(CERTIFICATION).build())
						.password(
								PasswordData.builder().password(passwordEncoder.encode(PASSWORD_SOURCE)).build())
						.build());
	}

	@Override
	public Optional<AuthenticationEntity> findByCertificationAndDeletedFalse(
			CertificationData certification) {
		return Optional.of(
				AuthenticationEntity.builder()
						.id(AUTHENTICATION_ID)
						.memberId(MEMBER_ID)
						.certification(CertificationData.builder().certification(CERTIFICATION).build())
						.password(
								PasswordData.builder().password(passwordEncoder.encode(PASSWORD_SOURCE)).build())
						.build());
	}

	@Override
	public AuthenticationEntity saveAuthentication(AuthenticationEntity authenticationEntity) {
		return authenticationEntity.toBuilder().id(AUTHENTICATION_ID).build();
	}
}
