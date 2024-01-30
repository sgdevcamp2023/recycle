package com.zzaug.member.domain.external.dao.auth;

import com.zzaug.member.entity.auth.BlackTokenAuthEntity;
import com.zzaug.member.persistence.auth.BlackTokenAuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Profile("!usecase-test")
@Repository
@RequiredArgsConstructor
public class BlackTokenAuthDaoImpl implements BlackTokenAuthDao {

	private final BlackTokenAuthRepository blackTokenAuthRepository;

	@Override
	public BlackTokenAuthEntity saveBlackTokenAuth(BlackTokenAuthEntity blackTokenAuthEntity) {
		return blackTokenAuthRepository.save(blackTokenAuthEntity);
	}
}
