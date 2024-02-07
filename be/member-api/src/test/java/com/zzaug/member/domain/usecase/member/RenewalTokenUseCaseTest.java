package com.zzaug.member.domain.usecase.member;

import com.zzaug.member.MemberApp;
import com.zzaug.member.domain.dto.member.MemberAuthToken;
import com.zzaug.member.domain.dto.member.RenewalTokenUseCaseRequest;
import com.zzaug.member.domain.usecase.AbstractUseCaseTest;
import com.zzaug.member.domain.usecase.config.mock.repository.UMockAuthenticationDao;
import com.zzaug.member.domain.usecase.config.mock.repository.UMockBlackTokenAuthDao;
import com.zzaug.member.domain.usecase.config.mock.repository.UMockExternalContactDao;
import com.zzaug.member.domain.usecase.validator.MemberAuthTokenValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
		classes = {
			MemberApp.class,
			UMockBlackTokenAuthDao.class,
			UMockAuthenticationDao.class,
			UMockExternalContactDao.class
		})
class RenewalTokenUseCaseTest extends AbstractUseCaseTest {

	@Autowired private RenewalTokenUseCase renewalTokenUseCase;
	@Autowired private MemberAuthTokenValidator memberAuthTokenValidator;

	@Test
	void 토큰_재발급_요청을_진행합니다() {
		RenewalTokenUseCaseRequest request =
				RenewalTokenUseCaseRequest.builder()
						.refreshToken(UMockBlackTokenAuthDao.SAMPLE_TOKEN)
						.accessToken(UMockBlackTokenAuthDao.SAMPLE_TOKEN)
						.build();

		MemberAuthToken response = renewalTokenUseCase.execute(request);

		// Then
		memberAuthTokenValidator.delegate(response);
	}
}
