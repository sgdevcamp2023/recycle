package com.zzaug.member.persistence.auth;

import static org.junit.jupiter.api.Assertions.*;

import com.zzaug.member.entity.auth.EmailAuthEntity;
import com.zzaug.member.entity.auth.EmailData;
import com.zzaug.member.persistence.AbstractRepositoryTest;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class EmailAuthRepositoryTest extends AbstractRepositoryTest {

	@Autowired EmailAuthRepository repository;

	static final Long memberId = 1L;
	static final EmailData email = EmailData.builder().email("sample@email.com").build();
	static final String nonce = "nonce";
	static final String code = "code";

	@BeforeEach
	void setUp() {

		repository.deleteAll();
		EmailAuthEntity entity =
				EmailAuthEntity.builder().memberId(1L).email(email).nonce(nonce).code(code).build();
		repository.save(entity);
	}

	@Test
	void 멤버가_요청한_이메일_인증정보를_이메일과_넌스값을_통해_조회한다() {
		// given

		// when
		Optional<EmailAuthEntity> result =
				repository.findByMemberIdAndEmailAndNonceAndDeletedFalse(memberId, email, nonce);

		// then
		assertTrue(result.isPresent());
		EmailAuthEntity source = result.get();
		Assertions.assertAll(
				() -> assertEquals(source.getMemberId(), memberId),
				() -> assertEquals(source.getEmail(), email),
				() -> assertEquals(source.getNonce(), nonce),
				() -> assertEquals(source.getCode(), code));
	}
}
