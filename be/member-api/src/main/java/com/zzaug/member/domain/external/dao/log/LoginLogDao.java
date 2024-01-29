package com.zzaug.member.domain.external.dao.log;

import com.zzaug.member.entity.log.LoginLogEntity;

public interface LoginLogDao {

	LoginLogEntity saveLoginLog(LoginLogEntity entity);
}
