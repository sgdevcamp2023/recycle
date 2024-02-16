package com.zzaug.member.domain.usecase.config.mock.security;

import com.zzaug.member.domain.external.security.auth.BlackTokenAuthCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.context.annotation.Profile;

@Profile("usecase-test")
@TestComponent
@RequiredArgsConstructor
public class UMockBlackTokenAuthCommand implements BlackTokenAuthCommand {

	@Override
	public void execute(String accessToken, String refreshToken) {
		// Do nothing
	}
}
