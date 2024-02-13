package com.zzaug.member.domain.external.security.auth;

import com.zzaug.security.persistence.transaction.SecurityTransactional;
import com.zzaug.security.redis.auth.BlackAuthTokenHash;
import com.zzaug.security.redis.auth.BlackAuthTokenHashRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Profile("!usecase-test")
@Component
@RequiredArgsConstructor
public class EnrollBlackTokenCacheServiceImpl implements EnrollTokenCacheService {

	private final BlackAuthTokenHashRepository blackAuthTokenHashRepository;

	@Override
	@SecurityTransactional
	public void execute(String token) {
		blackAuthTokenHashRepository.save(BlackAuthTokenHash.builder().token(token).build());
	}

	@Override
	public void execute(String accessToken, String refreshToken) {
		blackAuthTokenHashRepository.saveAll(
				List.of(
						BlackAuthTokenHash.builder().token(accessToken).build(),
						BlackAuthTokenHash.builder().token(refreshToken).build()));
	}
}
