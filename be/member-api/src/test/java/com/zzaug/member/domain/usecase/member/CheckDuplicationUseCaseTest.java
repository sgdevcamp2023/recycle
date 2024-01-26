package com.zzaug.member.domain.usecase.member;

import static org.junit.jupiter.api.Assertions.*;

import com.zzaug.member.MemberApp;
import com.zzaug.member.domain.dto.member.CheckDuplicationUseCaseRequest;
import com.zzaug.member.domain.dto.member.CheckDuplicationUseCaseResponse;
import com.zzaug.member.domain.usecase.AbstractUseCaseTest;
import com.zzaug.member.domain.usecase.config.mock.repository.UMockAuthenticationDao;
import com.zzaug.member.domain.usecase.config.mock.repository.UMockMemberSourceDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
		classes = {MemberApp.class, UMockAuthenticationDao.class, UMockMemberSourceDao.class})
class CheckDuplicationUseCaseTest extends AbstractUseCaseTest {

	@Autowired private CheckDuplicationUseCase checkDuplicationUseCase;

	@Test
	void 아이디가_중복되면_duplication이_false입니다() {
		// Given
		CheckDuplicationUseCaseRequest request =
				CheckDuplicationUseCaseRequest.builder().certification("sample@email.com").build();

		// When
		CheckDuplicationUseCaseResponse response = checkDuplicationUseCase.execute(request);

		// Then
		assertFalse(response.getDuplication());
	}
}
