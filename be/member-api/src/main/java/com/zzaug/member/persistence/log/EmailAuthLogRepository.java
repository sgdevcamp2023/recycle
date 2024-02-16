package com.zzaug.member.persistence.log;

import com.zzaug.member.entity.log.EmailAuthLogEntity;
import com.zzaug.member.persistence.support.checker.DeletedFalse;
import com.zzaug.member.persistence.support.checker.EmailAuthId;
import com.zzaug.member.persistence.support.checker.MemberId;
import com.zzaug.member.persistence.support.checker.ZzuagRepository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

@ZzuagRepository
public interface EmailAuthLogRepository extends JpaRepository<EmailAuthLogEntity, Long> {

	@MemberId
	@EmailAuthId
	@DeletedFalse
	Optional<EmailAuthLogEntity> findByMemberIdAndEmailAuthIdAndReasonNotAndDeletedFalse(
			Long memberId, Long emailAuthId, String reason);
}
