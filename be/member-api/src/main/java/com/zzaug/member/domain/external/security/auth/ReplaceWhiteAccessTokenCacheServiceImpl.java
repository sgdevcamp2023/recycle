package com.zzaug.member.domain.external.security.auth;

import com.zzaug.security.persistence.transaction.SecurityTransactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Slf4j
@Profile("!usecase-test")
@Service
@RequiredArgsConstructor
public class ReplaceWhiteAccessTokenCacheServiceImpl implements ReplaceTokenCacheService {

	@Value("${security.jwt.token.validtime.access}")
	private Long accessTokenValidTime;

	private final EvictWhiteTokenCacheServiceImpl evictTokenCacheService;
	private final EnrollWhiteAccessTokenCacheServiceImpl enrollTokenCacheService;
	private final EnrollBlackTokenCacheServiceImpl enrollBlackTokenCacheService;

	@Override
	@SecurityTransactional
	public void execute(String oldToken, String newToken, Long memberId) {
		log.debug("Evict old token.\ntoken: {}", oldToken);
		evictTokenCacheService.execute(oldToken);
		log.debug("Enroll old token to black list.\ntoken: {}", oldToken);
		enrollBlackTokenCacheService.execute(oldToken, 1000 * 60L, memberId);
		log.debug("Enroll new token.\ntoken: {}", newToken);
		enrollTokenCacheService.execute(newToken, accessTokenValidTime, memberId);
	}
}
