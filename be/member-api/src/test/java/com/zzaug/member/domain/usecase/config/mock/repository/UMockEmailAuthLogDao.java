package com.zzaug.member.domain.usecase.config.mock.repository;

import com.zzaug.member.domain.external.dao.log.EmailAuthLogDao;
import com.zzaug.member.entity.log.EmailAuthLogEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeansException;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Profile;

/**
 * 테스트용 이메일 인증 로그 DAO
 *
 * <p>요청한 멤버 ID와 이메일 인증 ID에 대한 이메일 인증 로그를 반환합니다.
 *
 * <p>프로파일이 over-max-try-count인 경우 인증 시도 횟수가 3회입니다.
 *
 * <p>프로파일이 under-max-try-count인 경우 인증 시도 횟수가 2회입니다.
 *
 * <p>프로파일이 설정되지 않은 경우 빈 값을 반환합니다.
 */
@Profile("usecase-test")
@TestComponent
public class UMockEmailAuthLogDao implements EmailAuthLogDao, ApplicationContextAware {

	public static final Long MAX_TRY_COUNT = 3L;
	public static final Long UNDER_MAX_TRY_COUNT = 2L;

	private List<String> activeProfiles;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		activeProfiles = List.of(applicationContext.getEnvironment().getActiveProfiles());
	}

	@Override
	public Optional<EmailAuthLogEntity> findByMemberIdAndEmailAuthIdAndReasonNotAndDeletedFalse(
			Long memberId, Long emailAuthId, String reason) {
		if (activeProfiles.contains("over-max-try-count")) {
			return Optional.of(
					EmailAuthLogEntity.builder()
							.id(1L)
							.memberId(memberId)
							.emailAuthId(emailAuthId)
							.reason(reason)
							.tryCount(MAX_TRY_COUNT)
							.build());
		}
		if (activeProfiles.contains("under-max-try-count")) {
			return Optional.of(
					EmailAuthLogEntity.builder()
							.id(1L)
							.memberId(memberId)
							.emailAuthId(emailAuthId)
							.reason(reason)
							.tryCount(UNDER_MAX_TRY_COUNT)
							.build());
		}
		return Optional.empty();
	}

	@Override
	public EmailAuthLogEntity saveEmailAuthLog(EmailAuthLogEntity entity) {
		return entity;
	}
}
