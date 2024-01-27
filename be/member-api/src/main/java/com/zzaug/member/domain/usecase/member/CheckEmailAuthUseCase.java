package com.zzaug.member.domain.usecase.member;

import static com.zzaug.member.domain.model.auth.EmailAuthResult.NOT_MATCH_CODE;
import static com.zzaug.member.domain.model.auth.EmailAuthResult.SUCCESS;

import com.zzaug.member.domain.dto.member.CheckEmailAuthUseCaseRequest;
import com.zzaug.member.domain.dto.member.CheckEmailAuthUseCaseResponse;
import com.zzaug.member.domain.external.dao.auth.EmailAuthDao;
import com.zzaug.member.domain.external.dao.log.EmailAuthLogDao;
import com.zzaug.member.domain.external.dao.member.ExternalContactDao;
import com.zzaug.member.domain.external.service.auth.EmailAuthLogService;
import com.zzaug.member.domain.external.service.member.MemberSourceQuery;
import com.zzaug.member.domain.model.auth.EmailAuthSource;
import com.zzaug.member.domain.model.auth.TryCountElement;
import com.zzaug.member.domain.model.member.MemberSource;
import com.zzaug.member.domain.support.entity.EmailAuthSourceConverter;
import com.zzaug.member.entity.auth.EmailAuthEntity;
import com.zzaug.member.entity.auth.EmailData;
import com.zzaug.member.entity.log.EmailAuthLogEntity;
import com.zzaug.member.entity.member.ContactType;
import com.zzaug.member.entity.member.ExternalContactEntity;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CheckEmailAuthUseCase {

	private final ExternalContactDao externalContactDao;
	private final EmailAuthDao emailAuthDao;
	private final EmailAuthLogDao emailAuthLogDao;

	private final MemberSourceQuery memberSourceQuery;
	private final EmailAuthLogService emailAuthLogService;

	@Transactional
	public CheckEmailAuthUseCaseResponse execute(CheckEmailAuthUseCaseRequest request) {
		final Long memberId = request.getMemberId();
		final String code = request.getCode();
		final EmailData email = EmailData.builder().email(request.getEmail()).build();
		final String nonce = request.getNonce();

		// todo 이메일을 요청한 세션과 동일한 세션에서 이메일 번호 확인 요청을 하였는지 확인

		MemberSource memberSource = memberSourceQuery.execute(memberId);

		EmailAuthSource emailAuth = getEmailAuth(memberSource, email, nonce);
		final Long emailAuthId = emailAuth.getId();

		// 이메일 인증 요청한 멤버와 요청한 멤버가 일치하는지 확인
		if (!emailAuth.isMemberId(memberSource.getId())) {
			throw new IllegalArgumentException("request member id is not matched");
		}
		// 이메일 인증 요청시 발급한 nonce와 요청한 nonce가 일치하는지 확인
		if (!emailAuth.isNonce(nonce)) {
			throw new IllegalArgumentException("request nonce is not matched");
		}

		// 이메일 인증을 시도한 횟수를 조회
		TryCountElement tryCount = getTryCount(memberId, emailAuthId);

		// 이메일 인증 요청시 발급한 code와 요청한 code가 일치하는지 확인
		if (!emailAuth.isCode(code)) {
			EmailAuthLogEntity emailAuthLogEntity =
					emailAuthLogService.saveLog(NOT_MATCH_CODE, tryCount, memberId, emailAuthId);
			return CheckEmailAuthUseCaseResponse.builder()
					.authentication(false)
					.tryCount(emailAuthLogEntity.getTryCount())
					.build();
		}
		// 일치하는 이메일을 외부 연락처로 저장
		ExternalContactEntity externalContactEntity =
				ExternalContactEntity.builder()
						.contactType(ContactType.EMAIL)
						.source(email.getEmail())
						.build();
		externalContactDao.saveContact(externalContactEntity);
		EmailAuthLogEntity emailAuthLogEntity =
				emailAuthLogService.saveLog(SUCCESS, tryCount, memberId, emailAuthId);

		// todo 이메일 인증 요청 세션을 삭제한다.
		return CheckEmailAuthUseCaseResponse.builder()
				.authentication(true)
				.tryCount(emailAuthLogEntity.getTryCount())
				.build();
	}

	private EmailAuthSource getEmailAuth(MemberSource memberSource, EmailData email, String nonce) {
		Optional<EmailAuthEntity> emailAuthSource =
				emailAuthDao.findByMemberIdAndEmailAndNonceAndDeletedFalse(
						memberSource.getId(), email, nonce);
		if (emailAuthSource.isEmpty()) {
			throw new IllegalArgumentException("request email auth information is not found");
		}
		return EmailAuthSourceConverter.from(emailAuthSource.get());
	}

	private TryCountElement getTryCount(Long memberId, Long emailAuthId) {
		// 이메일 인증을 실패한 이력이 있는지 조회
		Optional<EmailAuthLogEntity> emailAuthLogSource =
				emailAuthLogDao.findByMemberIdAndEmailAuthIdAndReasonNotAndDeletedFalse(
						memberId, emailAuthId, SUCCESS.getReason());
		int tryCount;
		if (emailAuthLogSource.isEmpty()) {
			// 이메일 인증을 실패한 이력이 없는 경우 tryCount를 0으로 초기화
			tryCount = 0;
			return TryCountElement.builder().tryCount(tryCount).build();
		} else {
			// 이메일 인증을 실패한 이력이 있는 경우 tryCount를 조회하여 초기화
			tryCount = Math.toIntExact(emailAuthLogSource.get().getTryCount());
			int maxTryCount = 3;
			if (tryCount >= maxTryCount) {
				// todo 이메일 인증 요청 세션이 존재하는지 확인하고 삭제한다.
				throw new IllegalArgumentException("request try count is over");
			}
			return TryCountElement.builder()
					.tryCount(tryCount)
					.emailAuthLogId(emailAuthLogSource.get().getId())
					.build();
		}
	}
}
