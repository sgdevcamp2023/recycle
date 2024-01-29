package com.zzaug.member.domain.external.dao.log;

import com.zzaug.member.entity.log.LoginLogEntity;
import com.zzaug.member.entity.log.LoginStatus;
import java.util.Optional;

public interface LoginLogDao {

	LoginLogEntity saveLoginLog(LoginLogEntity entity);

	Optional<LoginLogEntity> findTopByMemberIdAndStatusAndDeletedFalse(
			Long memberId, LoginStatus status);
}
