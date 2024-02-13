package com.zzaug.member.domain.external.security;

import com.zzaug.security.token.TokenResolver;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthTokenValidator {

	private final TokenResolver tokenResolver;

	public boolean execute(String token, Long memberId) {
		Optional<Long> resolveId = tokenResolver.resolveId(token);
		if (resolveId.isEmpty()) {
			log.error("Token is invalid \n token: {}", token);
			return false;
		}
		if (!resolveId.get().equals(memberId)) {
			log.warn("token is not matched with memberId: {} \n token: {}", memberId, token);
			return false;
		}
		return true;
	}

	public void execute(String refreshToken, String accessToken, Long memberId) {
		if (!execute(refreshToken, memberId)) {
			throw new IllegalArgumentException("Refresh token is invalid");
		}
		if (!execute(accessToken, memberId)) {
			throw new IllegalArgumentException("Access token is invalid");
		}
	}
}
