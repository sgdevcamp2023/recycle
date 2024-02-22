package com.zzaug.member.domain.usecase.validator;

import static com.zzaug.security.authentication.authority.Roles.ROLE_USER;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.zzaug.member.domain.dto.member.MemberAuthToken;
import com.zzaug.member.domain.usecase.config.mock.repository.UMockAuthenticationDao;
import com.zzaug.security.token.TokenResolver;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;

@TestComponent
@RequiredArgsConstructor
public class MemberAuthTokenValidator {

	@Autowired private TokenResolver tokenResolver;

	public void delegate(MemberAuthToken token) {
		String accessToken = token.getAccessToken();
		String refreshToken = token.getRefreshToken();
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
}
