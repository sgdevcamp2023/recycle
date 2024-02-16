package com.zzaug.member.persistence.member;

import static org.junit.jupiter.api.Assertions.*;

import com.zzaug.member.entity.member.MemberEntity;
import com.zzaug.member.entity.member.MemberStatus;
import com.zzaug.member.persistence.AbstractRepositoryTest;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class MemberRepositoryTest extends AbstractRepositoryTest {

	@Autowired MemberRepository repository;
	static Long memberId = 0L;

	@BeforeEach
	void setUp() {
		repository.deleteAll();
		MemberEntity entity = MemberEntity.builder().build();
		repository.save(entity);
		memberId = entity.getId();
		assert memberId != 0L;
	}

	@Test
	void 정상상태의_멤버를_조회한다() {
		// given

		// when
		Optional<MemberEntity> result =
				repository.findByIdAndStatusAndDeletedFalse(memberId, MemberStatus.REGULAR);

		// then
		assertTrue(result.isPresent());
		MemberEntity source = result.get();
		assertEquals(source.getId(), memberId);
	}
}
