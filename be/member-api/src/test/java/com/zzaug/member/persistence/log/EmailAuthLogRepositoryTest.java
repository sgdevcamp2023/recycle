package com.zzaug.member.persistence.log;

import static org.junit.jupiter.api.Assertions.*;

import com.zzaug.member.entity.log.EmailAuthLogEntity;
import com.zzaug.member.persistence.AbstractRepositoryTest;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class EmailAuthLogRepositoryTest extends AbstractRepositoryTest {

	@Autowired EmailAuthLogRepository repository;

	static final Long memberId = 1L;
	static final Long emailAuthId = 1L;

	@BeforeEach
	void setUp() {
		repository.deleteAll();
		EmailAuthLogEntity entity =
				EmailAuthLogEntity.builder()
						.memberId(memberId)
						.emailAuthId(emailAuthId)
						.reason("FAIL")
						.build();
		repository.save(entity);
	}

	@Test
	void 멤버의_이메일_인증_로그를_조회한다() {
		// given

		// when
		Optional<EmailAuthLogEntity> result =
				repository.findByMemberIdAndEmailAuthIdAndReasonNotAndDeletedFalse(
						memberId, emailAuthId, "SUCCESS");

		// then
		assertTrue(result.isPresent());
		EmailAuthLogEntity source = result.get();
		Assertions.assertAll(
				() -> assertEquals(source.getMemberId(), memberId),
				() -> assertEquals(source.getEmailAuthId(), emailAuthId),
				() -> assertEquals(source.getReason(), "FAIL"),
				() -> assertEquals(source.getTryCount(), 0L));
	}
}
