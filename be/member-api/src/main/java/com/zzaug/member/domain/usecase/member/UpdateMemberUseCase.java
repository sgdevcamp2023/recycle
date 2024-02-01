package com.zzaug.member.domain.usecase.member;

import com.zzaug.member.domain.dto.member.UpdateMemberUseCaseRequest;
import com.zzaug.member.domain.external.dao.member.AuthenticationDao;
import com.zzaug.member.domain.external.service.member.MemberSourceQuery;
import com.zzaug.member.domain.model.member.MemberAuthentication;
import com.zzaug.member.domain.model.member.MemberSource;
import com.zzaug.member.domain.support.entity.MemberAuthenticationConverter;
import com.zzaug.member.entity.member.AuthenticationEntity;
import com.zzaug.member.entity.member.CertificationData;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateMemberUseCase {

	private final AuthenticationDao authenticationDao;

	private final MemberSourceQuery memberSourceQuery;

	private final PasswordEncoder passwordEncoder;

	@Transactional
	public void execute(UpdateMemberUseCaseRequest request) {
		final Long memberId = request.getMemberId();
		final CertificationData certification =
				CertificationData.builder().certification(request.getCertification()).build();
		final String password = request.getPassword();

		MemberSource memberSource = memberSourceQuery.execute(memberId);

		log.debug("Get authentication. memberId: {}", memberId);
		Optional<AuthenticationEntity> authenticationSource =
				authenticationDao.findByMemberIdAndDeletedFalse(memberSource.getId());
		if (authenticationSource.isEmpty()) {
			throw new IllegalArgumentException("Not found authentication.");
		}
		MemberAuthentication memberAuthentication =
				MemberAuthenticationConverter.from(authenticationSource.get());

		if (!memberAuthentication.isMatchPassword(passwordEncoder, password)) {
			throw new IllegalArgumentException("Password is not matched.");
		}

		if (!memberAuthentication.isSameCertification(certification.getCertification())) {
			// todo false이면 certification을 기준으로 락을 걸어 처리 해야 함
			log.debug(
					"Check duplicate certification. certification: {}", certification.getCertification());
			boolean isDuplicateId = authenticationDao.existsByCertificationAndDeletedFalse(certification);
			if (isDuplicateId) {
				throw new IllegalArgumentException("Already exist certification.");
			}
			String originCertificationValue = memberAuthentication.getCertification();
			memberAuthentication.updateCertification(certification.getCertification());
			log.debug(
					"Update certification. memberId: {}, originCertification: {}, newCertification: {}",
					memberId,
					originCertificationValue,
					certification.getCertification());
		}

		AuthenticationEntity editAuthentication =
				MemberAuthenticationConverter.to(memberAuthentication);
		AuthenticationEntity authenticationEntity =
				authenticationDao.saveAuthentication(editAuthentication);
		log.debug(
				"Update authentication. memberId: {}, authenticationId: {}",
				memberId,
				authenticationEntity.getId());
	}
}
