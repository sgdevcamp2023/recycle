package com.zzaug.member.domain.usecase.member;

import com.zzaug.member.MemberApp;
import com.zzaug.member.domain.dto.member.DeleteMemberUseCaseRequest;
import com.zzaug.member.domain.usecase.AbstractUseCaseTest;
import com.zzaug.member.domain.usecase.config.mock.repository.UMockMemberSourceDao;
import com.zzaug.member.domain.usecase.config.mock.security.UMockBlackTokenAuthCommand;
import com.zzaug.member.domain.usecase.config.mock.security.UMockEvictTokenCacheService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
		classes = {
			MemberApp.class,
			UMockMemberSourceDao.class,
			UMockBlackTokenAuthCommand.class,
			UMockEvictTokenCacheService.class
		})
class DeleteMemberUseCaseTest extends AbstractUseCaseTest {
	@Value("${token.test}")
	public String token;

	@Autowired private DeleteMemberUseCase deleteMemberUseCase;

	@Test
	void 회원을_탈퇴합니다() {
		DeleteMemberUseCaseRequest request =
				DeleteMemberUseCaseRequest.builder()
						.memberId(UMockMemberSourceDao.MEMBER_ID)
						.accessToken(token)
						.refreshToken(token)
						.build();

		deleteMemberUseCase.execute(request);
	}
}
