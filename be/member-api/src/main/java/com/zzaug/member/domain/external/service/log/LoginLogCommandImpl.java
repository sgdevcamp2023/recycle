package com.zzaug.member.domain.external.service.log;

import com.zzaug.member.domain.external.dao.log.LoginLogDao;
import com.zzaug.member.entity.log.LoginLogEntity;
import com.zzaug.member.entity.log.LoginStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginLogCommandImpl implements LoginLogCommand {

	private final LoginLogDao loginLogDao;

	@Override
	@Transactional
	public void saveLoginLog(Long memberId, String userAgent) {
		loginLogDao.saveLoginLog(
				LoginLogEntity.builder()
						.status(LoginStatus.LOGIN)
						.memberId(memberId)
						.userAgent(userAgent)
						.build());
	}
}
