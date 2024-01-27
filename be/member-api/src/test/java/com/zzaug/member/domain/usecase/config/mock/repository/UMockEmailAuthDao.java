package com.zzaug.member.domain.usecase.config.mock.repository;

import com.zzaug.member.domain.external.dao.auth.EmailAuthDao;
import com.zzaug.member.entity.auth.EmailAuthEntity;
import com.zzaug.member.entity.auth.EmailData;
import java.util.Optional;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.context.annotation.Profile;

@Profile("usecase-test")
@TestComponent
public class UMockEmailAuthDao implements EmailAuthDao {

	@Override
	public Optional<EmailAuthEntity> findByMemberIdAndEmailAndNonceAndDeletedFalse(
			Long memberId, EmailData email, String nonce) {
		String code = "thisiscode";
		return Optional.of(EmailAuthEntity.builder().email(email).nonce(nonce).code(code).build());
	}

	@Override
	public EmailAuthEntity saveEmailAuth(EmailAuthEntity emailAuthEntity) {
		return emailAuthEntity;
	}
}
