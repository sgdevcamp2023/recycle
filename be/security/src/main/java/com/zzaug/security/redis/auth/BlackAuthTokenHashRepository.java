package com.zzaug.security.redis.auth;

import org.springframework.data.repository.CrudRepository;

public interface BlackAuthTokenHashRepository extends CrudRepository<BlackAuthTokenHash, String> {

	boolean existsByToken(String token);
}
