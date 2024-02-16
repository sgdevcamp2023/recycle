package com.zzaug.member.redis.email;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface EmailAuthSessionRepository extends CrudRepository<EmailAuthSession, String> {

	Optional<EmailAuthSession> findBySessionId(String sessionId);
}
