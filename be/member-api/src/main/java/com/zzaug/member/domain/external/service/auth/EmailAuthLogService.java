package com.zzaug.member.domain.external.service.auth;

import com.zzaug.member.domain.model.auth.EmailAuthResult;
import com.zzaug.member.domain.model.auth.TryCountElement;
import com.zzaug.member.entity.log.EmailAuthLogEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailAuthLogService {

	private final EmailAuthLogCommand emailAuthLogCommand;

	@Transactional
	public EmailAuthLogEntity saveLog(
			EmailAuthResult result, TryCountElement tryCount, Long memberId, Long emailAuthId) {
		String reason = result.getReason();
		if (!result.isSuccess()) {
			tryCount.plus();
		}
		if (tryCount.isNew()) {
			// 새로운 이메일 인증 로그를 저장한다.
			return emailAuthLogCommand.execute(
					tryCount.getEmailAuthLogId(),
					memberId,
					emailAuthId,
					reason,
					(long) tryCount.getTryCount());
		} else {
			// 이미 존재하는 이메일 인증 로그를 갱신한다.
			return emailAuthLogCommand.execute(
					memberId, emailAuthId, reason, (long) tryCount.getTryCount());
		}
	}
}
