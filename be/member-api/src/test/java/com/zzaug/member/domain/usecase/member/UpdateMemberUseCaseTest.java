package com.zzaug.member.domain.usecase.member;

import com.zzaug.member.MemberApp;
import com.zzaug.member.domain.dto.member.UpdateMemberUseCaseRequest;
import com.zzaug.member.domain.exception.PasswordNotMatchException;
import com.zzaug.member.domain.usecase.AbstractUseCaseTest;
import com.zzaug.member.domain.usecase.config.mock.repository.UMockAuthenticationDao;
import com.zzaug.member.domain.usecase.config.mock.repository.UMockMemberSourceDao;
import com.zzaug.member.domain.usecase.config.mock.service.UMockMemberSourceQuery;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
		classes = {MemberApp.class, UMockAuthenticationDao.class, UMockMemberSourceQuery.class})
class UpdateMemberUseCaseTest extends AbstractUseCaseTest {

	@Autowired private UpdateMemberUseCase updateMemberUseCase;

	@Test
	void CERTIFICATION을_수정합니다() {
		UpdateMemberUseCaseRequest request =
				UpdateMemberUseCaseRequest.builder()
						.memberId(UMockMemberSourceDao.MEMBER_ID)
						.certification("edit")
						.password(UMockAuthenticationDao.PASSWORD_SOURCE)
						.build();

		updateMemberUseCase.execute(request);
	}

	@Test
	void 비밀번호가_일치하지_않으면_수정할_수_없습니다() {
		UpdateMemberUseCaseRequest request =
				UpdateMemberUseCaseRequest.builder()
						.memberId(UMockMemberSourceDao.MEMBER_ID)
						.certification(UMockAuthenticationDao.CERTIFICATION)
						.password("wrongpassword")
						.build();

		Assertions.assertThatThrownBy(() -> updateMemberUseCase.execute(request))
				.isInstanceOf(PasswordNotMatchException.class);
	}
}
