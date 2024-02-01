package com.zzaug.member.domain.usecase.member;

import com.zzaug.member.domain.dto.member.EmailAuthUseCaseRequest;
import com.zzaug.member.domain.dto.member.EmailAuthUseCaseResponse;
import com.zzaug.member.domain.external.dao.auth.EmailAuthDao;
import com.zzaug.member.domain.external.dao.member.ExternalContactDao;
import com.zzaug.member.entity.auth.EmailAuthEntity;
import com.zzaug.member.entity.auth.EmailData;
import com.zzaug.member.entity.member.ContactType;
import com.zzaug.member.redis.email.EmailAuthSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailAuthUseCase {

	private final ExternalContactDao externalContactDao;
	private final EmailAuthDao emailAuthDao;

	@Transactional
	public EmailAuthUseCaseResponse execute(EmailAuthUseCaseRequest request) {
		final Long memberId = request.getMemberId();
		final String sessionId = request.getSessionId();
		final EmailData email = EmailData.builder().email(request.getEmail()).build();
		final String nonce = request.getNonce();
		final String authCode = RandomStringUtils.random(7, true, true);

		boolean isEmailExist =
				externalContactDao.existsByContactTypeAndSourceAndDeletedFalse(
						ContactType.EMAIL, email.getEmail());
		if (isEmailExist) {
			return EmailAuthUseCaseResponse.builder().duplication(true).build();
		}
		// todo 너무 많은 이메일 인증 요청을 보내는 것은 아닌지 확인한다.

		save(memberId, email, nonce, authCode, sessionId);

		// todo 이메일 인증 요청 메시지를 보낸다.
		return EmailAuthUseCaseResponse.builder().duplication(false).build();
	}

	private void save(
			Long memberId, EmailData email, String nonce, String authCode, String sessionId) {
		EmailAuthEntity emailAuthEntity = saveEmailAuthEntity(memberId, email, nonce, authCode);
		saveEmailSession(memberId, sessionId, emailAuthEntity.getId());
	}

	private EmailAuthEntity saveEmailAuthEntity(
			Long memberId, EmailData email, String nonce, String authCode) {
		EmailAuthEntity emailAuthSource =
				EmailAuthEntity.builder()
						.memberId(memberId)
						.email(email)
						.nonce(nonce)
						.code(authCode)
						.build();
		return emailAuthDao.saveEmailAuth(emailAuthSource);
	}

	private void saveEmailSession(Long memberId, String sessionId, Long emailAuthId) {
		EmailAuthSession emailAuthSession =
				EmailAuthSession.builder()
						.memberId(memberId)
						.emailAuthId(emailAuthId)
						.sessionId(sessionId)
						.build();
		emailAuthDao.saveEmailAuthSession(emailAuthSession);
	}
}
