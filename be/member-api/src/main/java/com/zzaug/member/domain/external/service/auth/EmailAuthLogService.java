package com.zzaug.member.domain.external.service.auth;

import com.zzaug.member.domain.model.auth.EmailAuthResult;
import com.zzaug.member.domain.model.auth.TryCountElement;
import com.zzaug.member.entity.log.EmailAuthLogEntity;
import com.zzaug.member.persistence.support.transaction.UseCaseTransactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailAuthLogService {

	private final EmailAuthLogCommand emailAuthLogCommand;

	@UseCaseTransactional
	public EmailAuthLogEntity saveLog(
			TryCountElement tryCount, Long memberId, Long emailAuthId, String reason) {
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

	public TryCountElement calculateTryCount(EmailAuthResult result, TryCountElement tryCount) {
		int originValue = tryCount.getTryCount();
		if (!result.isSuccess()) {
			tryCount.plus();
		}
		log.debug("tryCount : {} -> {}", originValue, tryCount.getTryCount());
		return tryCount;
	}
}
