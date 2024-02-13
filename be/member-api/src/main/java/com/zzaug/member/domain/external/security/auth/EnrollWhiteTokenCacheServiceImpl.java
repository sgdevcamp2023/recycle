package com.zzaug.member.domain.external.security.auth;

import com.zzaug.security.persistence.transaction.SecurityTransactional;
import com.zzaug.security.redis.auth.WhiteAuthTokenHash;
import com.zzaug.security.redis.auth.WhiteAuthTokenHashRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Profile("!usecase-test")
@Component
@RequiredArgsConstructor
public class EnrollWhiteTokenCacheServiceImpl implements EnrollTokenCacheService {

	private final WhiteAuthTokenHashRepository whiteAuthTokenHashRepository;

	@Override
	@SecurityTransactional
	public void execute(String token) {
		whiteAuthTokenHashRepository.save(WhiteAuthTokenHash.builder().token(token).build());
	}

	@Override
	public void execute(String accessToken, String refreshToken) {
		whiteAuthTokenHashRepository.saveAll(
				List.of(
						WhiteAuthTokenHash.builder().token(accessToken).build(),
						WhiteAuthTokenHash.builder().token(refreshToken).build()));
	}
}
