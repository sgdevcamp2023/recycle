package com.zzaug.member.domain.external.security.auth;

import com.zzaug.security.entity.auth.BlackTokenAuthEntity;
import com.zzaug.security.entity.auth.TokenData;
import com.zzaug.security.entity.auth.TokenType;
import com.zzaug.security.persistence.auth.BlackTokenAuthRepository;
import com.zzaug.security.persistence.transaction.SecurityTransactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Profile("!usecase-test")
@Component
@RequiredArgsConstructor
public class BlackTokenAuthCommandImpl implements BlackTokenAuthCommand {

	private final BlackTokenAuthRepository blackTokenAuthRepository;

	@Override
	@SecurityTransactional
	public void execute(String accessToken, String refreshToken) {
		blackTokenAuthRepository.saveAll(
				List.of(
						BlackTokenAuthEntity.builder()
								.token(TokenData.builder().token(accessToken).build())
								.tokenType(TokenType.ACCESSTOKEN)
								.build(),
						BlackTokenAuthEntity.builder()
								.token(TokenData.builder().token(refreshToken).build())
								.tokenType(TokenType.REFRESHTOKEN)
								.build()));
	}
}
