package com.zzaug.member.domain.external.service.log;

import com.zzaug.member.domain.external.dao.log.LoginLogDao;
import com.zzaug.member.domain.model.log.LoginLog;
import com.zzaug.member.domain.support.entity.LoginLogConverter;
import com.zzaug.member.entity.log.LoginLogEntity;
import com.zzaug.member.entity.log.LoginStatus;
import com.zzaug.member.persistence.support.transaction.UseCaseTransactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginLogCommandImpl implements LoginLogCommand {

	private final LoginLogDao loginLogDao;

	@Override
	@UseCaseTransactional
	public void saveLoginLog(Long memberId, String userAgent) {
		loginLogDao.saveLoginLog(
				LoginLogEntity.builder()
						.status(LoginStatus.LOGIN)
						.memberId(memberId)
						.userAgent(userAgent)
						.build());
	}

	@Override
	public void saveLogoutLog(LoginLog loginLog) {
		LoginLogEntity loginLogEntity = LoginLogConverter.to(loginLog);
		loginLogDao.saveLoginLog(loginLogEntity);
	}
}
