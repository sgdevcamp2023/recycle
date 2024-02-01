package com.zzaug.member.redis.email;

import static org.junit.jupiter.api.Assertions.*;

import com.zzaug.member.redis.AbstractRedisTest;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class EmailAuthSessionRepositoryTest extends AbstractRedisTest {

	@Autowired EmailAuthSessionRepository repository;

	static final Long memberId = 1L;
	static final Long emailAuthId = 1L;
	static final String sessionId = "session-id";

	@BeforeEach
	void setUp() {
		repository.deleteAll();
		EmailAuthSession entity =
				EmailAuthSession.builder()
						.memberId(memberId)
						.emailAuthId(emailAuthId)
						.sessionId(sessionId)
						.build();
		repository.save(entity);
	}

	@Test
	void 세션_아이디_값을_통해_조회한다() {
		// Given

		// When
		Optional<EmailAuthSession> emailAuthSessionSource = repository.findBySessionId(sessionId);

		// Then
		assertTrue(emailAuthSessionSource.isPresent());
		EmailAuthSession emailAuthSession = emailAuthSessionSource.get();
		assertAll(
				() -> assertEquals(emailAuthSession.getMemberId(), memberId),
				() -> assertEquals(emailAuthSession.getEmailAuthId(), emailAuthId),
				() -> assertEquals(emailAuthSession.getSessionId(), sessionId));
	}
}
