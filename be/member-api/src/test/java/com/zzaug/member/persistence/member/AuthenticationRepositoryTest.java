package com.zzaug.member.persistence.member;

import static org.junit.jupiter.api.Assertions.*;

import com.zzaug.member.entity.member.AuthenticationEntity;
import com.zzaug.member.entity.member.CertificationData;
import com.zzaug.member.entity.member.PasswordData;
import com.zzaug.member.persistence.AbstractRepositoryTest;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class AuthenticationRepositoryTest extends AbstractRepositoryTest {

	@Autowired AuthenticationRepository repository;

	static final Long memberId = 1L;
	static final CertificationData certification =
			CertificationData.builder().certification("certification").build();
	static final PasswordData password = PasswordData.builder().password("password").build();

	@BeforeEach
	void setUp() {
		repository.deleteAll();
		AuthenticationEntity entity =
				AuthenticationEntity.builder()
						.memberId(memberId)
						.certification(certification)
						.password(password)
						.build();
		repository.save(entity);
	}

	@Test
	void 인증정보가_존재하는지_확인한다() {
		// given

		// when
		boolean result = repository.existsByCertificationAndDeletedFalse(certification);

		// then
		assertTrue(result);
	}

	@Test
	void 인증정보를_조회한다() {
		// given

		// when
		Optional<AuthenticationEntity> result =
				repository.findByCertificationAndDeletedFalse(certification);

		// then
		assertTrue(result.isPresent());
		AuthenticationEntity source = result.get();
		Assertions.assertAll(
				() -> assertEquals(source.getMemberId(), memberId),
				() -> assertEquals(source.getCertification(), certification),
				() -> assertEquals(source.getPassword(), password));
	}

	@Test
	void 멤버의_인증정보를_조회한다() {
		// given

		// when
		Optional<AuthenticationEntity> result = repository.findByMemberIdAndDeletedFalse(memberId);

		// then
		assertTrue(result.isPresent());
		AuthenticationEntity source = result.get();
		Assertions.assertAll(
				() -> assertEquals(source.getMemberId(), memberId),
				() -> assertEquals(source.getCertification(), certification),
				() -> assertEquals(source.getPassword(), password));
	}
}
