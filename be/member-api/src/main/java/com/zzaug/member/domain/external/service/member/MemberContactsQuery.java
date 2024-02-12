package com.zzaug.member.domain.external.service.member;

import com.zzaug.member.domain.model.member.GetMemberId;
import com.zzaug.member.domain.model.member.MemberContacts;

public interface MemberContactsQuery {

	MemberContacts execute(GetMemberId memberId);
}
