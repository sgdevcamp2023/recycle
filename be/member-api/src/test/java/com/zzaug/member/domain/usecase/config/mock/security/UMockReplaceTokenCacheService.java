package com.zzaug.member.domain.usecase.config.mock.security;

import com.zzaug.member.domain.external.security.auth.ReplaceTokenCacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.context.annotation.Profile;

@Profile("usecase-test")
@TestComponent
@RequiredArgsConstructor
public class UMockReplaceTokenCacheService implements ReplaceTokenCacheService {

	@Override
	public void execute(String oldToken, String newToken, Long memberId) {
		// Do nothing
	}
}
