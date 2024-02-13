package com.zzaug.member.domain.usecase.member;

import com.zzaug.member.domain.dto.member.LogOutUseCaseRequest;
import com.zzaug.member.domain.event.LogOutEvent;
import com.zzaug.member.domain.external.dao.log.LoginLogDao;
import com.zzaug.member.domain.external.security.AuthTokenValidator;
import com.zzaug.member.domain.external.security.auth.BlackTokenAuthCommand;
import com.zzaug.member.domain.external.security.auth.EnrollTokenCacheService;
import com.zzaug.member.domain.external.security.auth.EvictTokenCacheService;
import com.zzaug.member.domain.external.service.log.LoginLogCommand;
import com.zzaug.member.domain.model.log.LoginLog;
import com.zzaug.member.domain.support.entity.LoginLogConverter;
import com.zzaug.member.entity.log.LoginLogEntity;
import com.zzaug.member.entity.log.LoginStatus;
import com.zzaug.member.persistence.support.transaction.UseCaseTransactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LogOutUseCase {

	private final LoginLogDao loginLogDao;
	private final LoginLogCommand loginLogCommand;

	private final ApplicationEventPublisher applicationEventPublisher;

	// security
	private final AuthTokenValidator authTokenValidator;
	private final BlackTokenAuthCommand blackTokenAuthCommand;
	private final EvictTokenCacheService evictWhiteTokenCacheServiceImpl;
	private final EnrollTokenCacheService enrollBlackTokenCacheServiceImpl;

	@UseCaseTransactional
	public void execute(LogOutUseCaseRequest request) {
		final Long memberId = request.getMemberId();
		final String accessToken = request.getAccessToken();
		final String refreshToken = request.getRefreshToken();

		authTokenValidator.execute(refreshToken, accessToken, memberId);

		Optional<LoginLogEntity> loginLogSource =
				loginLogDao.findTopByMemberIdAndStatusAndDeletedFalse(memberId, LoginStatus.LOGIN);

		if (loginLogSource.isEmpty()) {
			log.warn("login log not found. memberId: {}", memberId);
			return;
		}
		LoginLog loginLog = LoginLogConverter.from(loginLogSource.get());

		loginLogCommand.saveLogoutLog(loginLog);

		blackTokenAuthCommand.execute(accessToken, refreshToken);
		enrollBlackTokenCacheServiceImpl.execute(accessToken, refreshToken);
		evictWhiteTokenCacheServiceImpl.execute(accessToken);

		publishEvent(memberId);
	}

	private void publishEvent(Long memberId) {
		applicationEventPublisher.publishEvent(LogOutEvent.builder().memberId(memberId).build());
	}
}
