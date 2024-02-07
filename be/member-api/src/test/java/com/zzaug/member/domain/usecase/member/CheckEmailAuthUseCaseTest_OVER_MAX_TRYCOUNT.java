package com.zzaug.member.domain.usecase.member;

import com.zzaug.member.MemberApp;
import com.zzaug.member.domain.dto.member.CheckEmailAuthUseCaseRequest;
import com.zzaug.member.domain.dto.member.CheckEmailAuthUseCaseResponse;
import com.zzaug.member.domain.usecase.AbstractUseCaseTest;
import com.zzaug.member.domain.usecase.config.mock.repository.UMockEmailAuthDao;
import com.zzaug.member.domain.usecase.config.mock.repository.UMockEmailAuthLogDao;
import com.zzaug.member.domain.usecase.config.mock.repository.UMockExternalContactDao;
import com.zzaug.member.domain.usecase.config.mock.service.UMockMemberSourceQuery;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = {"over-max-try-count"})
@SpringBootTest(
		classes = {
			MemberApp.class,
			UMockExternalContactDao.class,
			UMockEmailAuthDao.class,
			UMockEmailAuthLogDao.class,
			UMockMemberSourceQuery.class
		})
class CheckEmailAuthUseCaseTest_OVER_MAX_TRYCOUNT extends AbstractUseCaseTest {

	@Autowired private CheckEmailAuthUseCase checkEmailAuthUseCase;

	Long memberId = 1L;
	String code = UMockEmailAuthDao.CODE;
	String email = "sample@email.com";
	String nonce = "nonce";
	final CheckEmailAuthUseCaseRequest request =
			CheckEmailAuthUseCaseRequest.builder()
					.memberId(memberId)
					.code(code)
					.email(email)
					.nonce(nonce)
					.build();

	@Test
	void 이메일_인증시도_초과로_인한_실패() {
		// Given

		// When
		CheckEmailAuthUseCaseResponse response = checkEmailAuthUseCase.execute(request);

		// Then
		org.junit.jupiter.api.Assertions.assertAll(
			() -> Assertions.assertThat(response.getAuthentication()).isFalse(),
			() -> Assertions.assertThat(response.getTryCount()).isEqualTo(3L));
	}
}
