package com.zzaug.member.domain.external.security.auth;

import com.zzaug.security.persistence.transaction.SecurityTransactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Slf4j
@Profile("!usecase-test")
@Service
@RequiredArgsConstructor
public class ReplaceWhiteTokenCacheServiceImpl implements ReplaceTokenCacheService {

	private final EvictWhiteTokenCacheServiceImpl evictTokenCacheService;
	private final EnrollWhiteTokenCacheServiceImpl enrollTokenCacheService;
	private final EnrollBlackTokenCacheServiceImpl enrollBlackTokenCacheService;

	@Override
	@SecurityTransactional
	public void execute(String oldToken, String newToken) {
		log.debug("Evict old token.\ntoken: {}", oldToken);
		evictTokenCacheService.execute(oldToken);
		log.debug("Enroll old token to black list.\ntoken: {}", oldToken);
		enrollBlackTokenCacheService.execute(oldToken);
		log.debug("Enroll new token.\ntoken: {}", newToken);
		enrollTokenCacheService.execute(newToken);
	}
}
