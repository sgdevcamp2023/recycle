package com.zzaug.member.domain.usecase.member;

import static org.assertj.core.api.Assertions.assertThat;

import com.zzaug.member.MemberApp;
import com.zzaug.member.domain.dto.member.GetMemberUseCaseRequest;
import com.zzaug.member.domain.dto.member.GetMemberUseCaseResponse;
import com.zzaug.member.domain.usecase.AbstractUseCaseTest;
import com.zzaug.member.domain.usecase.config.mock.repository.UMockExternalContactDao;
import com.zzaug.member.domain.usecase.config.mock.service.UMockMemberSourceQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
		classes = {MemberApp.class, UMockMemberSourceQuery.class, UMockExternalContactDao.class})
class GetMemberUseCaseTest extends AbstractUseCaseTest {

	@Autowired private GetMemberUseCase getMemberUseCase;

	@Test
	void 멤버_정보를_조회합니다() {
		Long memberId = 1L;
		GetMemberUseCaseRequest request =
				GetMemberUseCaseRequest.builder().queryMemberId(memberId).build();

		GetMemberUseCaseResponse response = getMemberUseCase.execute(request);

		Assertions.assertAll(
				() -> assertThat(response.getId()).isEqualTo(memberId),
				() -> assertThat(response.getEmail()).isEqualTo(UMockExternalContactDao.EMAIL),
				() -> assertThat(response.getGithub()).isEqualTo(UMockExternalContactDao.GITHUB));
	}
}
