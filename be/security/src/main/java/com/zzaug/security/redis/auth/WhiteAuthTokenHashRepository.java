package com.zzaug.security.redis.auth;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface WhiteAuthTokenHashRepository extends CrudRepository<WhiteAuthTokenHash, String> {

	boolean existsByToken(String token);

	Optional<WhiteAuthTokenHash> findByToken(String token);
}
