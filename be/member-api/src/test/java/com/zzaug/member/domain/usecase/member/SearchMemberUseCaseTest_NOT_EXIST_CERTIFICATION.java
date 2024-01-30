package com.zzaug.member.domain.usecase.member;

import com.zzaug.member.MemberApp;
import com.zzaug.member.domain.dto.member.SearchMemberUseCaseRequest;
import com.zzaug.member.domain.usecase.AbstractUseCaseTest;
import com.zzaug.member.domain.usecase.config.mock.repository.UMockAuthenticationDao;
import com.zzaug.member.domain.usecase.config.mock.repository.UMockExternalContactDao;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("not-exist-certification")
@SpringBootTest(
		classes = {MemberApp.class, UMockAuthenticationDao.class, UMockExternalContactDao.class})
class SearchMemberUseCaseTest_NOT_EXIST_CERTIFICATION extends AbstractUseCaseTest {

	@Autowired private SearchMemberUseCase searchMemberUseCase;

	@Test
	void 없는_증명을_통해_회원_검색_요청을_진행합니다() {
		// Given
		SearchMemberUseCaseRequest request =
				SearchMemberUseCaseRequest.builder().certification("not exist certification").build();

		// When
		IllegalArgumentException exception =
				org.junit.jupiter.api.Assertions.assertThrows(
						IllegalArgumentException.class, () -> searchMemberUseCase.execute(request));

		// Then
		Assertions.assertThat(exception.getMessage()).isEqualTo("인증 정보가 존재하지 않습니다.");
	}
}
