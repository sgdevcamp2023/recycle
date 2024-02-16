package com.zzaug.member.persistence.member;

import static org.junit.jupiter.api.Assertions.*;

import com.zzaug.member.entity.member.ContactType;
import com.zzaug.member.entity.member.ExternalContactEntity;
import com.zzaug.member.persistence.AbstractRepositoryTest;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class ExternalContactRepositoryTest extends AbstractRepositoryTest {

	@Autowired ExternalContactRepository repository;

	static final Long memberId = 1L;
	static final String email = "sample@email.com";

	@BeforeEach
	void setUp() {
		repository.deleteAll();
		ExternalContactEntity entity =
				ExternalContactEntity.builder()
						.memberId(memberId)
						.contactType(ContactType.EMAIL)
						.source(email)
						.build();
		repository.save(entity);
	}

	@Test
	void 멤버의_외부_연락처가_존재하는지_확인한다() {
		// given

		// when
		boolean result =
				repository.existsByContactTypeAndSourceAndDeletedFalse(ContactType.EMAIL, email);

		// then
		assertTrue(result);
	}

	@Test
	void 멤버의_외부_연락처를_조회한다() {
		// given

		// when
		Optional<ExternalContactEntity> result =
				repository.findByMemberIdAndContactTypeAndDeletedFalse(memberId, ContactType.EMAIL);

		// then
		assertTrue(result.isPresent());
		ExternalContactEntity source = result.get();
		Assertions.assertAll(
				() -> assertEquals(source.getMemberId(), memberId),
				() -> assertEquals(source.getContactType(), ContactType.EMAIL),
				() -> assertEquals(source.getSource(), email));
	}

	@Test
	void 멤버의_외부_연락처_목록을_조회한다() {
		// given

		// when
		List<ExternalContactEntity> result = repository.findAllByMemberIdAndDeletedFalse(memberId);

		// then

		for (ExternalContactEntity source : result) {
			Assertions.assertAll(
					() -> assertEquals(source.getMemberId(), memberId),
					() -> assertEquals(source.getContactType(), ContactType.EMAIL),
					() -> assertEquals(source.getSource(), email));
		}
	}
}
