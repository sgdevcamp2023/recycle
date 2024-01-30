package com.zzaug.member.domain.usecase.member;

import com.zzaug.member.domain.dto.member.SearchMemberUseCaseRequest;
import com.zzaug.member.domain.dto.member.SearchMemberUseCaseResponse;
import com.zzaug.member.domain.external.dao.member.AuthenticationDao;
import com.zzaug.member.domain.external.dao.member.ExternalContactDao;
import com.zzaug.member.domain.model.member.GetMemberId;
import com.zzaug.member.domain.model.member.MemberContacts;
import com.zzaug.member.domain.support.entity.MemberAuthenticationConverter;
import com.zzaug.member.domain.support.entity.MemberContactExtractor;
import com.zzaug.member.entity.member.AuthenticationEntity;
import com.zzaug.member.entity.member.CertificationData;
import com.zzaug.member.entity.member.ExternalContactEntity;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchMemberUseCase {

	private final AuthenticationDao authenticationDao;
	private final ExternalContactDao externalContactDao;

	public SearchMemberUseCaseResponse execute(SearchMemberUseCaseRequest request) {
		final CertificationData certification =
				CertificationData.builder().certification(request.getCertification()).build();

		Optional<AuthenticationEntity> authenticationSource =
				authenticationDao.findByCertificationAndDeletedFalse(certification);
		if (authenticationSource.isEmpty()) {
			throw new IllegalArgumentException("인증 정보가 존재하지 않습니다.");
		}
		GetMemberId memberAuthentication =
				MemberAuthenticationConverter.from(authenticationSource.get());

		List<ExternalContactEntity> contacts =
				externalContactDao.findAllByMemberIdAndDeletedFalse(memberAuthentication.getMemberId());
		MemberContacts memberContacts = MemberContactExtractor.execute(contacts);

		return SearchMemberUseCaseResponse.builder()
				.id(memberAuthentication.getMemberId())
				.email(memberContacts.getEmail())
				.github(memberContacts.getGithub())
				.build();
	}
}
