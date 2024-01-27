package com.zzaug.member.domain.external.dao.auth;

import com.zzaug.member.entity.auth.EmailAuthEntity;
import com.zzaug.member.entity.auth.EmailData;
import java.util.Optional;

public interface EmailAuthDao {
	Optional<EmailAuthEntity> findByMemberIdAndEmailAndNonceAndDeletedFalse(
			Long memberId, EmailData email, String nonce);

	EmailAuthEntity saveEmailAuth(EmailAuthEntity emailAuthEntity);
}
