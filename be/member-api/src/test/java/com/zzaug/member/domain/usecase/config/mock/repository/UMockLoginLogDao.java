package com.zzaug.member.domain.usecase.config.mock.repository;

import com.zzaug.member.domain.external.dao.log.LoginLogDao;
import com.zzaug.member.entity.log.LoginLogEntity;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.context.annotation.Profile;

/**
 * 테스트용 로그인 로그 DAO
 *
 * <p>요청한 로그인 로그를 저장합니다.
 */
@Profile("usecase-test")
@TestComponent
public class UMockLoginLogDao implements LoginLogDao {

	@Override
	public LoginLogEntity saveLoginLog(LoginLogEntity entity) {
		return entity;
	}
}
