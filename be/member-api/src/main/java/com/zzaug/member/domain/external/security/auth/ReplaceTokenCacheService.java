package com.zzaug.member.domain.external.security.auth;

public interface ReplaceTokenCacheService {

	void execute(String oldToken, String newToken, Long memberId);
}
