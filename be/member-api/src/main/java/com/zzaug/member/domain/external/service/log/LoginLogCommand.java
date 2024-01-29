package com.zzaug.member.domain.external.service.log;

public interface LoginLogCommand {

	void saveLoginLog(Long memberId, String userAgent);
}
