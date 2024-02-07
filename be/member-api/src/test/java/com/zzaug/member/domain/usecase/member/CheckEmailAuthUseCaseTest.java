package com.zzaug.member.domain.usecase.member;

import static org.junit.jupiter.api.Assertions.*;

import com.zzaug.member.MemberApp;
import com.zzaug.member.domain.dto.member.CheckEmailAuthUseCaseRequest;
import com.zzaug.member.domain.dto.member.CheckEmailAuthUseCaseResponse;
import com.zzaug.member.domain.usecase.AbstractUseCaseTest;
import com.zzaug.member.domain.usecase.config.mock.repository.UMockEmailAuthDao;
import com.zzaug.member.domain.usecase.config.mock.repository.UMockEmailAuthLogDao;
import com.zzaug.member.domain.usecase.config.mock.repository.UMockExternalContactDao;
import com.zzaug.member.domain.usecase.config.mock.security.UMockBlackTokenAuthCommand;
import com.zzaug.member.domain.usecase.config.mock.security.UMockEvictTokenCacheService;
import com.zzaug.member.domain.usecase.config.mock.service.UMockMemberSourceQuery;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
		classes = {
			MemberApp.class,
			UMockExternalContactDao.class,
			UMockEmailAuthDao.class,
			UMockEmailAuthLogDao.class,
			UMockMemberSourceQuery.class,
			UMockBlackTokenAuthCommand.class,
			UMockEvictTokenCacheService.class
		})
class CheckEmailAuthUseCaseTest extends AbstractUseCaseTest {

	@Value("${token.test}")
	public String token;

	@Autowired private CheckEmailAuthUseCase checkEmailAuthUseCase;
	Long memberId = 1L;
	String code = UMockEmailAuthDao.CODE;
	String email = "sample@email.com";
	String nonce = "nonce";

	String sessionId = "sessionId";

	CheckEmailAuthUseCaseRequest request;

	@BeforeEach
	void setUp() {
		request =
				CheckEmailAuthUseCaseRequest.builder()
						.memberId(memberId)
						.code(code)
						.email(email)
						.nonce(nonce)
						.sessionId(sessionId)
						.refreshToken(token)
						.accessToken(token)
						.build();
	}

	@Test
	void 이메일_인증_성공() {
		// Given

		// When
		CheckEmailAuthUseCaseResponse response = checkEmailAuthUseCase.execute(request);

		// Then
		assertTrue(response.getAuthentication());
		Assertions.assertThat(response.getTryCount()).isZero();
	}

	@Test
	void 코드_불일치로인한_이메일_인증_실패() {
		// Given
		CheckEmailAuthUseCaseRequest wrongCodeRequest =
				CheckEmailAuthUseCaseRequest.builder()
						.memberId(memberId)
						.code("wrong code")
						.email(email)
						.nonce(nonce)
						.sessionId(sessionId)
						.refreshToken(token)
						.accessToken(token)
						.build();

		// When
		CheckEmailAuthUseCaseResponse response = checkEmailAuthUseCase.execute(wrongCodeRequest);

		// Then
		assertFalse(response.getAuthentication());
		Assertions.assertThat(response.getTryCount()).isEqualTo(1L);
	}
}
