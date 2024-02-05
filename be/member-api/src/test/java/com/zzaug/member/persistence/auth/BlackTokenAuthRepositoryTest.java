package com.zzaug.member.persistence.auth;

import static org.junit.jupiter.api.Assertions.*;

import com.zzaug.member.entity.auth.BlackTokenAuthEntity;
import com.zzaug.member.entity.auth.TokenData;
import com.zzaug.member.entity.auth.TokenType;
import com.zzaug.member.persistence.AbstractRepositoryTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class BlackTokenAuthRepositoryTest extends AbstractRepositoryTest {

	@Autowired BlackTokenAuthRepository repository;
	static final TokenData token = TokenData.builder().token("aaa.bbb.ccc").build();

	@BeforeEach
	void setUp() {
		repository.deleteAll();
		BlackTokenAuthEntity entity =
				BlackTokenAuthEntity.builder().token(token).tokenType(TokenType.ACCESSTOKEN).build();
		repository.save(entity);
	}

	@Test
	void 토큰이_존재하는지_확인한다() {
		// given

		// when
		boolean result = repository.existsByTokenAndDeletedFalse(token);

		// then
		assertTrue(result);
	}
}
