package com.zzaug.member.domain.external.dao.auth;

import com.zzaug.member.entity.auth.EmailAuthEntity;
import com.zzaug.member.entity.auth.EmailData;
import com.zzaug.member.persistence.auth.EmailAuthRepository;
import com.zzaug.member.redis.email.EmailAuthSession;
import com.zzaug.member.redis.email.EmailAuthSessionRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Profile("!usecase-test")
@Repository
@RequiredArgsConstructor
public class EmailAuthDaoImpl implements EmailAuthDao {

	private final EmailAuthRepository emailAuthRepository;

	private final EmailAuthSessionRepository emailAuthSessionRepository;

	@Override
	public Optional<EmailAuthEntity> findByMemberIdAndEmailAndNonceAndDeletedFalse(
			Long memberId, EmailData email, String nonce) {
		return emailAuthRepository.findByMemberIdAndEmailAndNonceAndDeletedFalse(
				memberId, email, nonce);
	}

	@Override
	public EmailAuthEntity saveEmailAuth(EmailAuthEntity emailAuthEntity) {
		return emailAuthRepository.save(emailAuthEntity);
	}

	@Override
	public EmailAuthSession saveEmailAuthSession(EmailAuthSession emailAuthSession) {
		return emailAuthSessionRepository.save(emailAuthSession);
	}
}
