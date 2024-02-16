package com.zzaug.member.persistence.auth;

import com.zzaug.member.entity.auth.EmailAuthEntity;
import com.zzaug.member.entity.auth.EmailData;
import com.zzaug.member.persistence.support.checker.DeletedFalse;
import com.zzaug.member.persistence.support.checker.MemberId;
import com.zzaug.member.persistence.support.checker.ZzuagRepository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

@ZzuagRepository
public interface EmailAuthRepository extends JpaRepository<EmailAuthEntity, Long> {

	@MemberId
	@DeletedFalse
	Optional<EmailAuthEntity> findByMemberIdAndEmailAndNonceAndDeletedFalse(
			Long memberId, EmailData email, String nonce);
}
