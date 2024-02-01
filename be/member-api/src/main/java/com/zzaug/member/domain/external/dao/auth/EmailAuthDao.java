package com.zzaug.member.domain.external.dao.auth;

import com.zzaug.member.entity.auth.EmailAuthEntity;
import com.zzaug.member.entity.auth.EmailData;
import com.zzaug.member.redis.email.EmailAuthSession;
import java.util.Optional;

public interface EmailAuthDao {
	Optional<EmailAuthEntity> findByMemberIdAndEmailAndNonceAndDeletedFalse(
			Long memberId, EmailData email, String nonce);

	EmailAuthEntity saveEmailAuth(EmailAuthEntity emailAuthEntity);

	Optional<EmailAuthSession> findBySessionId(String sessionId);

	EmailAuthSession saveEmailAuthSession(EmailAuthSession emailAuthSession);

	void deleteBySessionId(String sessionId);
}
