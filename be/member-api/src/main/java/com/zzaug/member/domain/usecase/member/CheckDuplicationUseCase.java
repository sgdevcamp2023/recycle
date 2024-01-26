package com.zzaug.member.domain.usecase.member;

import com.zzaug.member.domain.dto.member.CheckDuplicationUseCaseRequest;
import com.zzaug.member.domain.dto.member.CheckDuplicationUseCaseResponse;
import com.zzaug.member.domain.external.dao.member.AuthenticationDao;
import com.zzaug.member.entity.member.CertificationData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CheckDuplicationUseCase {

	private final AuthenticationDao authenticationDao;

	public CheckDuplicationUseCaseResponse execute(CheckDuplicationUseCaseRequest certification) {
		final CertificationData certificationData =
				CertificationData.builder().certification(certification.getCertification()).build();

		boolean isDuplicateCertification =
				authenticationDao.existsByCertificationAndDeletedFalse(certificationData);
		if (isDuplicateCertification) {
			return CheckDuplicationUseCaseResponse.builder().duplication(true).build();
		}
		return CheckDuplicationUseCaseResponse.builder().duplication(false).build();
	}
}
