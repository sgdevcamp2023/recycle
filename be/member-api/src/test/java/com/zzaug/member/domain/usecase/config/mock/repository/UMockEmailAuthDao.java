package com.zzaug.member.domain.usecase.config.mock.repository;

import com.zzaug.member.domain.external.dao.auth.EmailAuthDao;
import com.zzaug.member.entity.auth.EmailAuthEntity;
import com.zzaug.member.entity.auth.EmailData;
import com.zzaug.member.redis.email.EmailAuthSession;
import java.util.Optional;
import java.util.UUID;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.context.annotation.Profile;

/**
 * 테스트용 이메일 인증 DAO
 *
 * <p>요청한 멤버 ID와 이메일, nonce에 대한 이메일 인증을 반환합니다.
 *
 * <p>코드는 고정값으로 설정되어 있습니다. "thisiscode"
 */
@Profile("usecase-test")
@TestComponent
public class UMockEmailAuthDao implements EmailAuthDao {

	public static final Long EMAIL_AUTH_ID = 1L;
	public static final String EMAIL_AUTH_SESSION_ID = UUID.randomUUID().toString();

	public static String EMAIL = "sample@email.com";
	public static String CODE = "thisiscode";

	@Override
	public Optional<EmailAuthEntity> findByMemberIdAndEmailAndNonceAndDeletedFalse(
			Long memberId, EmailData email, String nonce) {
		return Optional.of(
				EmailAuthEntity.builder()
						.id(EMAIL_AUTH_ID)
						.memberId(memberId)
						.email(email)
						.nonce(nonce)
						.code(CODE)
						.build());
	}

	@Override
	public EmailAuthEntity saveEmailAuth(EmailAuthEntity emailAuthEntity) {
		return emailAuthEntity;
	}

	@Override
	public Optional<EmailAuthSession> findBySessionId(String sessionId) {
		return Optional.of(
				EmailAuthSession.builder().memberId(1L).emailAuthId(1L).sessionId(sessionId).build());
	}

	@Override
	public EmailAuthSession saveEmailAuthSession(EmailAuthSession emailAuthSession) {
		return emailAuthSession.toBuilder().id(EMAIL_AUTH_SESSION_ID).build();
	}

	@Override
	public void deleteBySessionId(String sessionId) {
		// do nothing
	}
}
