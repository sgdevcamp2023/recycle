package com.zzaug.member.domain.external.security.auth;

import com.zzaug.security.persistence.transaction.SecurityTransactional;
import com.zzaug.security.redis.auth.WhiteAuthTokenHash;
import com.zzaug.security.redis.auth.WhiteAuthTokenHashRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Profile("!usecase-test")
@Component
@RequiredArgsConstructor
public class EvictWhiteTokenCacheServiceImpl implements EvictTokenCacheService {

	private final WhiteAuthTokenHashRepository whiteAuthTokenHashRepository;

	@Override
	@SecurityTransactional
	public void execute(String token) {
		Optional<WhiteAuthTokenHash> authTokenSource = whiteAuthTokenHashRepository.findByToken(token);
		if (authTokenSource.isEmpty()) {
			log.warn("auth token not found. token: {}", token);
			return;
		}
		WhiteAuthTokenHash whiteAuthTokenHash = authTokenSource.get();
		whiteAuthTokenHashRepository.deleteById(whiteAuthTokenHash.getId());
	}
}
