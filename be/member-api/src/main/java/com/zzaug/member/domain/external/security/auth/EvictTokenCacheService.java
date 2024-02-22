package com.zzaug.member.domain.external.security.auth;

public interface EvictTokenCacheService {

	void execute(String token);
}
