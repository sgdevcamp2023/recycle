package com.zzaug.member.domain.usecase.member;

import com.zzaug.member.domain.dto.member.GetMemberUseCaseRequest;
import com.zzaug.member.domain.dto.member.GetMemberUseCaseResponse;
import com.zzaug.member.domain.external.dao.member.ExternalContactDao;
import com.zzaug.member.domain.external.service.member.MemberSourceQuery;
import com.zzaug.member.domain.model.member.MemberContacts;
import com.zzaug.member.domain.model.member.MemberSource;
import com.zzaug.member.domain.support.entity.MemberContactExtractor;
import com.zzaug.member.entity.member.ExternalContactEntity;
import com.zzaug.member.persistence.support.transaction.UseCaseTransactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetMemberUseCase {

	private final MemberSourceQuery memberSourceQuery;

	private final ExternalContactDao externalContactRepository;

	@UseCaseTransactional(readOnly = true)
	public GetMemberUseCaseResponse execute(GetMemberUseCaseRequest request) {
		final Long queryMemberId = request.getQueryMemberId();

		MemberSource source = memberSourceQuery.execute(queryMemberId);

		// get member's contacts
		log.debug("Get member's contacts. memberId: {}", source.getId());
		List<ExternalContactEntity> contacts =
				externalContactRepository.findAllByMemberIdAndDeletedFalse(source.getId());
		MemberContacts memberContacts = MemberContactExtractor.execute(contacts);

		return GetMemberUseCaseResponse.builder()
				.id(source.getId())
				.email(memberContacts.getEmail())
				.github(memberContacts.getGithub())
				.build();
	}
}
