package com.zzaug.member.domain.usecase.config.mock.repository;

import com.zzaug.member.domain.external.dao.log.LoginLogDao;
import com.zzaug.member.entity.log.LoginLogEntity;
import com.zzaug.member.entity.log.LoginStatus;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeansException;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Profile;

/**
 * 테스트용 로그인 로그 DAO
 *
 * <p>요청한 로그인 로그를 저장합니다.
 *
 * <p>프로파일이 empty-login-log인 경우 로그인 로그가 없습니다.
 *
 * <p>프로파일이 usecase-test인 경우 로그인 로그가 있습니다.
 */
@Profile("usecase-test")
@TestComponent
public class UMockLoginLogDao implements LoginLogDao, ApplicationContextAware {

	public static final Long LOGIN_LOG_ID = 1L;
	public static final String USER_AGENT = "userAgent";

	private List<String> activeProfiles;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		activeProfiles = List.of(applicationContext.getEnvironment().getActiveProfiles());
	}

	@Override
	public LoginLogEntity saveLoginLog(LoginLogEntity entity) {
		return entity;
	}

	@Override
	public Optional<LoginLogEntity> findTopByMemberIdAndStatusAndDeletedFalse(
			Long memberId, LoginStatus status) {
		if (activeProfiles.contains("empty-login-log")) {
			return Optional.empty();
		}
		return Optional.of(
				LoginLogEntity.builder()
						.id(LOGIN_LOG_ID)
						.memberId(memberId)
						.status(status)
						.userAgent(USER_AGENT)
						.build());
	}
}
