package com.zzaug.member.domain.external.service.auth;

import com.zzaug.member.domain.external.dao.log.EmailAuthLogDao;
import com.zzaug.member.entity.log.EmailAuthLogEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailAuthLogCommandImpl implements EmailAuthLogCommand {

	private final EmailAuthLogDao emailAuthLogDao;

	@Override
	@Transactional
	public EmailAuthLogEntity execute(
			Long id, Long memberId, Long emailAuthId, String reason, Long tryCount) {
		EmailAuthLogEntity emailAuthLogEntity =
				EmailAuthLogEntity.builder()
						.id(id)
						.memberId(memberId)
						.emailAuthId(emailAuthId)
						.reason(reason)
						.tryCount(tryCount)
						.build();
		return emailAuthLogDao.saveEmailAuthLog(emailAuthLogEntity);
	}

	@Override
	@Transactional
	public EmailAuthLogEntity execute(Long memberId, Long emailAuthId, String reason, Long tryCount) {
		EmailAuthLogEntity emailAuthLogEntity =
				EmailAuthLogEntity.builder()
						.memberId(memberId)
						.emailAuthId(emailAuthId)
						.reason(reason)
						.tryCount(tryCount)
						.build();
		return emailAuthLogDao.saveEmailAuthLog(emailAuthLogEntity);
	}
}
