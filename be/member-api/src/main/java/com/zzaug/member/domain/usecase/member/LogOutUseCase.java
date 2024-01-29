package com.zzaug.member.domain.usecase.member;

import com.zzaug.member.domain.dto.member.LogOutUseCaseRequest;
import com.zzaug.member.domain.external.dao.log.LoginLogDao;
import com.zzaug.member.domain.external.service.log.LoginLogCommand;
import com.zzaug.member.domain.model.log.LoginLog;
import com.zzaug.member.domain.support.entity.LoginLogConverter;
import com.zzaug.member.entity.log.LoginLogEntity;
import com.zzaug.member.entity.log.LoginStatus;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LogOutUseCase {

	private final LoginLogDao loginLogDao;
	private final LoginLogCommand loginLogCommand;

	public void execute(LogOutUseCaseRequest request) {
		final Long memberId = request.getMemberId();
		Optional<LoginLogEntity> loginLogSource =
				loginLogDao.findTopByMemberIdAndStatusAndDeletedFalse(memberId, LoginStatus.LOGIN);

		if (loginLogSource.isEmpty()) {
			log.error("로그인 로그가 없습니다. memberId: {}", memberId);
			return;
		}
		LoginLog loginLog = LoginLogConverter.from(loginLogSource.get());

		loginLogCommand.saveLogoutLog(loginLog);
	}
}
