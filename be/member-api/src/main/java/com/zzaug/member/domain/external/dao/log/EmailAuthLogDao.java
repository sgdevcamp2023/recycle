package com.zzaug.member.domain.external.dao.log;

import com.zzaug.member.entity.log.EmailAuthLogEntity;
import java.util.Optional;

public interface EmailAuthLogDao {
	Optional<EmailAuthLogEntity> findByMemberIdAndEmailAuthIdAndReasonNotAndDeletedFalse(
			Long memberId, Long emailAuthId, String reason);

	EmailAuthLogEntity saveEmailAuthLog(EmailAuthLogEntity entity);
}
