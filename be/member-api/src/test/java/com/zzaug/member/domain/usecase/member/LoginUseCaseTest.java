package com.zzaug.member.domain.usecase.member;

import com.zzaug.member.MemberApp;
import com.zzaug.member.domain.dto.member.LoginUseCaseRequest;
import com.zzaug.member.domain.dto.member.MemberAuthToken;
import com.zzaug.member.domain.usecase.AbstractUseCaseTest;
import com.zzaug.member.domain.usecase.config.mock.repository.UMockAuthenticationDao;
import com.zzaug.member.domain.usecase.config.mock.repository.UMockExternalContactDao;
import com.zzaug.member.domain.usecase.config.mock.repository.UMockLoginLogDao;
import com.zzaug.member.domain.usecase.validator.MemberAuthTokenValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
		classes = {
			MemberApp.class,
			UMockAuthenticationDao.class,
			UMockExternalContactDao.class,
			UMockLoginLogDao.class
		})
class LoginUseCaseTest extends AbstractUseCaseTest {

	@Autowired private LoginUseCase loginUseCase;
	@Autowired private MemberAuthTokenValidator memberAuthTokenValidator;

	@Test
	void 로그인을_진행합니다() {
		// Given
		LoginUseCaseRequest request =
				LoginUseCaseRequest.builder()
						.certification(UMockAuthenticationDao.CERTIFICATION)
						.password(UMockAuthenticationDao.PASSWORD_SOURCE)
						.userAgent("userAgent")
						.build();

		// When
		MemberAuthToken response = loginUseCase.execute(request);

		// Then
		memberAuthTokenValidator.delegate(response);
	}

	@Test
	void 비밀번호가_일치하지_않는다면_로그인에_실패합니다() {
		// Given
		LoginUseCaseRequest request =
				LoginUseCaseRequest.builder()
						.certification(UMockAuthenticationDao.CERTIFICATION)
						.password("wrongPassword")
						.userAgent("userAgent")
						.build();

		// When
		org.assertj.core.api.Assertions.assertThatThrownBy(() -> loginUseCase.execute(request))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("비밀번호가 일치하지 않습니다.");
	}
}
