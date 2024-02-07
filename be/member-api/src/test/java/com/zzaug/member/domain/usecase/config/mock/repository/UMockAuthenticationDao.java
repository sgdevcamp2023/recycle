package com.zzaug.member.domain.usecase.config.mock.repository;

import com.zzaug.member.domain.external.dao.member.AuthenticationDao;
import com.zzaug.member.entity.member.AuthenticationEntity;
import com.zzaug.member.entity.member.CertificationData;
import java.util.List;
import org.springframework.beans.BeansException;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Profile;

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
public class UMockAuthenticationDao implements AuthenticationDao, ApplicationContextAware {

	public static final Long AUTHENTICATION_ID = 1L;

	private List<String> activeProfiles;

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
	public AuthenticationEntity saveAuthentication(AuthenticationEntity authenticationEntity) {
		return authenticationEntity.toBuilder().id(AUTHENTICATION_ID).build();
	}
}
