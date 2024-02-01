package com.zzaug.member.domain.usecase.config.mock.repository;

import com.zzaug.member.domain.external.dao.auth.BlackTokenAuthDao;
import com.zzaug.member.entity.auth.BlackTokenAuthEntity;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.context.annotation.Profile;

/**
 * 테스트용 블랙 토큰 인증 DAO
 *
 * <p>블랙 토큰 인증 정보를 저장합니다.
 */
@Profile("usecase-test")
@TestComponent
@RequiredArgsConstructor
public class UMockBlackTokenAuthDao implements BlackTokenAuthDao {

	public static final String SAMPLE_TOKEN =
			"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJtZW1iZXJJZCI6MSwiY2VydGlmaWNhdGlvbiI6InNhbXBsZSIsIm1lbWJlclJvbGUiOiJbUk9MRV9VU0VSXSIsImVtYWlsIjoic2FtcGxlQGVtYWlsLmNvbSIsImdpdGh1YiI6InNhbXBsZUdpdCIsImlhdCI6MTcwNjYyNTY5MH0.BKiUfu0k04f4WWYSFvA80g6PHGaGgnB0N5P39CD8ivA";

	@Override
	public BlackTokenAuthEntity saveBlackTokenAuth(BlackTokenAuthEntity blackTokenAuthEntity) {
		return blackTokenAuthEntity;
	}

	@Override
	public List<BlackTokenAuthEntity> saveAllBlackTokenAuth(
			List<BlackTokenAuthEntity> blackTokenAuthEntities) {
		return blackTokenAuthEntities;
	}
}
