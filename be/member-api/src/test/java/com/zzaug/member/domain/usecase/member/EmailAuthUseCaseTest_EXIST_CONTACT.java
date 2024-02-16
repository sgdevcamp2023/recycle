package com.zzaug.member.domain.usecase.member;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.zzaug.member.MemberApp;
import com.zzaug.member.domain.dto.member.EmailAuthUseCaseRequest;
import com.zzaug.member.domain.dto.member.EmailAuthUseCaseResponse;
import com.zzaug.member.domain.usecase.AbstractUseCaseTest;
import com.zzaug.member.domain.usecase.config.mock.repository.UMockEmailAuthDao;
import com.zzaug.member.domain.usecase.config.mock.repository.UMockExternalContactDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = {"exist-contact"})
@SpringBootTest(classes = {MemberApp.class, UMockExternalContactDao.class, UMockEmailAuthDao.class})
class EmailAuthUseCaseTest_EXIST_CONTACT extends AbstractUseCaseTest {

	@Autowired private EmailAuthUseCase emailAuthUseCase;

	@Test
	void 이메일_인증_요청() {
		// Given
		Long memberId = 1L;
		String sessionId = "thisissessionid";
		String email = "sample@email.com";
		String nonce = "abcdefgh";
		EmailAuthUseCaseRequest request =
				EmailAuthUseCaseRequest.builder()
						.memberId(memberId)
						.sessionId(sessionId)
						.email(email)
						.nonce(nonce)
						.build();

		// When
		EmailAuthUseCaseResponse response = emailAuthUseCase.execute(request);

		// Then
		assertTrue(response.getDuplication());
	}
}
