package com.zzaug.member.domain.external.service.log;

import com.zzaug.member.domain.model.log.LoginLog;

public interface LoginLogCommand {

	void saveLoginLog(Long memberId, String userAgent);

	void saveLogoutLog(LoginLog loginLog);
}
