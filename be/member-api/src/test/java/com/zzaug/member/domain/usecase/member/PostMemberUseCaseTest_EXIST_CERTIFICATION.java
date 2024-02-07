package com.zzaug.member.domain.usecase.member;

import com.zzaug.member.MemberApp;
import com.zzaug.member.domain.dto.member.PostMemberUseCaseRequest;
import com.zzaug.member.domain.exception.ExistSourceException;
import com.zzaug.member.domain.usecase.AbstractUseCaseTest;
import com.zzaug.member.domain.usecase.config.mock.repository.UMockAuthenticationDao;
import com.zzaug.member.domain.usecase.config.mock.repository.UMockMemberSourceDao;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = {"exist-certification"})
@SpringBootTest(
		classes = {MemberApp.class, UMockAuthenticationDao.class, UMockMemberSourceDao.class})
class PostMemberUseCaseTest_EXIST_CERTIFICATION extends AbstractUseCaseTest {

	@Autowired private PostMemberUseCase postMemberUseCase;

	@Test
	void 아이디가_중복되면_회원가입이_진행되지_않습니다() {
		PostMemberUseCaseRequest request =
				PostMemberUseCaseRequest.builder()
						.certification("certification123")
						.password("password@123")
						.build();

		Assertions.assertThatThrownBy(() -> postMemberUseCase.execute(request))
				.isInstanceOf(ExistSourceException.class);
	}
}
