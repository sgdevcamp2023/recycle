package com.zzaug.member.domain.usecase.member;

import static com.zzaug.security.authentication.authority.Roles.ROLE_USER;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.zzaug.member.MemberApp;
import com.zzaug.member.domain.dto.member.LoginUseCaseRequest;
import com.zzaug.member.domain.dto.member.MemberAuthToken;
import com.zzaug.member.domain.exception.PasswordNotMatchException;
import com.zzaug.member.domain.usecase.AbstractUseCaseTest;
import com.zzaug.member.domain.usecase.config.mock.repository.UMockAuthenticationDao;
import com.zzaug.member.domain.usecase.config.mock.repository.UMockExternalContactDao;
import com.zzaug.member.domain.usecase.config.mock.repository.UMockLoginLogDao;
import com.zzaug.security.token.TokenResolver;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
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
	@Autowired private TokenResolver tokenResolver;

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
		String accessToken = response.getAccessToken();
		String refreshToken = response.getRefreshToken();
		Optional<Long> accessTokenMemberId = tokenResolver.resolveId(accessToken);
		Optional<Long> refreshTokenMemberId = tokenResolver.resolveId(refreshToken);
		Optional<String> accessTokenRole = tokenResolver.resolveRole(accessToken);
		Optional<String> refreshTokenRole = tokenResolver.resolveRole(refreshToken);
		Assertions.assertAll(
				() -> assertThat(accessToken).isNotNull(),
				() -> assertThat(refreshToken).isNotNull(),
				() -> assertThat(accessTokenMemberId).isPresent(),
				() -> assertThat(refreshTokenMemberId).isPresent(),
				() -> assertThat(accessTokenMemberId).contains(UMockAuthenticationDao.MEMBER_ID),
				() -> assertThat(refreshTokenMemberId).contains(UMockAuthenticationDao.MEMBER_ID),
				() -> assertThat(accessTokenRole).isPresent(),
				() -> assertThat(refreshTokenRole).isPresent(),
				() -> assertThat(accessTokenRole).contains("[" + ROLE_USER.getRole() + "]"),
				() -> assertThat(refreshTokenRole).contains("[" + ROLE_USER.getRole() + "]"));
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
			.isInstanceOf(PasswordNotMatchException.class);
	}
}
