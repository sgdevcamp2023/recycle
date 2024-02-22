package com.zzaug.member.persistence.log;

import static org.junit.jupiter.api.Assertions.*;

import com.zzaug.member.entity.log.LoginLogEntity;
import com.zzaug.member.entity.log.LoginStatus;
import com.zzaug.member.persistence.AbstractRepositoryTest;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class LoginLogRepositoryTest extends AbstractRepositoryTest {

	@Autowired LoginLogRepository repository;

	static final Long memberId = 1L;

	@BeforeEach
	void setUp() {
		repository.deleteAll();
		LoginLogEntity entity =
				LoginLogEntity.builder()
						.memberId(memberId)
						.status(LoginStatus.LOGIN)
						.userAgent("agent")
						.build();
		repository.save(entity);
	}

	@Test
	void 멤버의_로그인_로그를_조회한다() {
		// given

		// when
		Optional<LoginLogEntity> result =
				repository.findTopByMemberIdAndStatusAndDeletedFalse(memberId, LoginStatus.LOGIN);

		// then
		assertTrue(result.isPresent());
		LoginLogEntity source = result.get();
		Assertions.assertAll(
				() -> assertEquals(source.getMemberId(), memberId),
				() -> assertEquals(source.getStatus(), LoginStatus.LOGIN),
				() -> assertEquals(source.getUserAgent(), "agent"));
	}
}
