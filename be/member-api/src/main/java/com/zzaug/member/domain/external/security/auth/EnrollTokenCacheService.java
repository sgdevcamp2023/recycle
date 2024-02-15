package com.zzaug.member.domain.external.security.auth;

public interface EnrollTokenCacheService {

	void execute(String token, Long ttl, Long memberId);

	void execute(String accessToken, String refreshToken);
}
