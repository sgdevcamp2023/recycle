package com.zzaug.member.domain.external.service.member;

import com.zzaug.member.domain.external.dao.member.ExternalContactDao;
import com.zzaug.member.domain.model.member.GetMemberId;
import com.zzaug.member.domain.model.member.MemberContacts;
import com.zzaug.member.domain.support.entity.MemberContactExtractor;
import com.zzaug.member.entity.member.ExternalContactEntity;
import com.zzaug.member.persistence.support.transaction.UseCaseTransactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MemberContactsQueryImpl implements MemberContactsQuery {

	private final ExternalContactDao externalContactDao;

	@Override
	@UseCaseTransactional(readOnly = true)
	public MemberContacts execute(GetMemberId memberId) {
		List<ExternalContactEntity> contacts =
				externalContactDao.findAllByMemberIdAndDeletedFalse(memberId.getMemberId());
		return MemberContactExtractor.execute(contacts);
	}
}
