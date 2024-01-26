package com.zzaug.member.domain.usecase.member;

import com.zzaug.member.domain.dto.member.GetMemberUseCaseRequest;
import com.zzaug.member.domain.dto.member.GetMemberUseCaseResponse;
import com.zzaug.member.domain.external.dao.member.ExternalContactDao;
import com.zzaug.member.domain.external.service.member.MemberSourceQuery;
import com.zzaug.member.domain.model.member.MemberSource;
import com.zzaug.member.entity.member.ContactType;
import com.zzaug.member.entity.member.ExternalContactEntity;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetMemberUseCase {

	private final MemberSourceQuery memberSourceQuery;

	private final ExternalContactDao externalContactRepository;

	@Transactional(readOnly = true)
	public GetMemberUseCaseResponse execute(GetMemberUseCaseRequest request) {
		final Long queryMemberId = request.getQueryMemberId();

		MemberSource source = memberSourceQuery.execute(queryMemberId);

		// get member's contacts
		List<ExternalContactEntity> contacts =
				externalContactRepository.findAllByMemberIdAndDeletedFalse(source.getId());
		MemberContacts memberContacts = extract(contacts);

		return GetMemberUseCaseResponse.builder()
				.id(source.getId())
				.email(memberContacts.getEmail())
				.github(memberContacts.getGithub())
				.build();
	}

	private MemberContacts extract(List<ExternalContactEntity> contacts) {
		String email = Strings.EMPTY;
		String github = Strings.EMPTY;
		for (ExternalContactEntity contact : contacts) {
			if (contact.getContactType().equals(ContactType.EMAIL)) {
				email = contact.getSource();
				continue;
			}
			if (contact.getContactType().equals(ContactType.GITHUB)) {
				github = contact.getSource();
			}
		}
		return MemberContacts.builder().email(email).github(github).build();
	}

	@Data
	@Builder
	private static class MemberContacts {
		private final String email;
		private final String github;
	}
}
