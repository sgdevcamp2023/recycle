package com.zzaug.member.domain.usecase.member;

import com.zzaug.member.domain.dto.member.EmailAuthUseCaseRequest;
import com.zzaug.member.domain.dto.member.EmailAuthUseCaseResponse;
import com.zzaug.member.domain.external.dao.auth.EmailAuthDao;
import com.zzaug.member.domain.external.dao.member.ExternalContactDao;
import com.zzaug.member.entity.auth.EmailAuthEntity;
import com.zzaug.member.entity.auth.EmailData;
import com.zzaug.member.entity.member.ContactType;
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
		EmailAuthEntity emailAuthSource =
				EmailAuthEntity.builder().email(email).nonce(nonce).code(authCode).build();
		EmailAuthEntity emailAuthEntity = emailAuthDao.saveEmailAuth(emailAuthSource);
		// todo 이메일 인증 요청을 보낸 세션을 저장한다. 이때 ttl 설정을 하여 일정 시간이 지나면 삭제되도록 한다.
		// todo 이메일 인증 요청 메시지를 보낸다.
		return EmailAuthUseCaseResponse.builder().duplication(false).build();
	}
}
