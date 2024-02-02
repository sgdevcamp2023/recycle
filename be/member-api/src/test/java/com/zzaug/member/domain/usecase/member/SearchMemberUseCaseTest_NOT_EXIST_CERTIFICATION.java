package com.zzaug.member.domain.usecase.member;

import com.zzaug.member.MemberApp;
import com.zzaug.member.domain.dto.member.SearchMemberUseCaseRequest;
import com.zzaug.member.domain.exception.SourceNotFoundException;
import com.zzaug.member.domain.usecase.AbstractUseCaseTest;
import com.zzaug.member.domain.usecase.config.mock.repository.UMockAuthenticationDao;
import com.zzaug.member.domain.usecase.config.mock.repository.UMockExternalContactDao;
import org.junit.jupiter.api.Assertions;
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
				SearchMemberUseCaseRequest.builder().certification("notExist").build();

		// When
		SourceNotFoundException exception =
				Assertions.assertThrows(
						SourceNotFoundException.class, () -> searchMemberUseCase.execute(request));

		// Then
		org.assertj.core.api.Assertions.assertThat(exception.getMessage()).contains("Certification");
	}
}
