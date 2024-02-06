package com.zzaug.security.persistence.auth;

import static org.junit.jupiter.api.Assertions.*;

import com.zzaug.security.entity.auth.BlackTokenAuthEntity;
import com.zzaug.security.entity.auth.TokenData;
import com.zzaug.security.entity.auth.TokenType;
import com.zzaug.security.persistence.AbstractRepositoryTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

class BlackTokenAuthRepositoryTest extends AbstractRepositoryTest {

	@Value("${token.test}")
	private String token;

	@Autowired BlackTokenAuthRepository repository;

	@BeforeEach
	void setUp() {
		repository.deleteAll();
		BlackTokenAuthEntity entity =
				BlackTokenAuthEntity.builder()
						.token(TokenData.builder().token(token).build())
						.tokenType(TokenType.ACCESSTOKEN)
						.build();
		repository.save(entity);
	}

	@Test
	void 토큰이_존재하는지_확인한다() {
		// given

		// when
		boolean result =
				repository.existsByTokenAndDeletedFalse(TokenData.builder().token(token).build());

		// then
		assertTrue(result);
	}
}
