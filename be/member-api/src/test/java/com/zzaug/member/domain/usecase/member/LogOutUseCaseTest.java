package com.zzaug.member.domain.usecase.member;

import com.zzaug.member.MemberApp;
import com.zzaug.member.domain.dto.member.LogOutUseCaseRequest;
import com.zzaug.member.domain.usecase.AbstractUseCaseTest;
import com.zzaug.member.domain.usecase.config.mock.repository.UMockAuthenticationDao;
import com.zzaug.member.domain.usecase.config.mock.repository.UMockLoginLogDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {MemberApp.class, UMockLoginLogDao.class})
class LogOutUseCaseTest extends AbstractUseCaseTest {

	@Autowired private LogOutUseCase logOutUseCase;

	@Test
	void 로그아웃을_진행합니다() {
		// Given
		LogOutUseCaseRequest request =
				LogOutUseCaseRequest.builder().memberId(UMockAuthenticationDao.MEMBER_ID).build();

		// When
		logOutUseCase.execute(request);
	}
}
