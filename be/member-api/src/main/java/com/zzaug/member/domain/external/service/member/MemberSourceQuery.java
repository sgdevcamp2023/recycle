package com.zzaug.member.domain.external.service.member;

import com.zzaug.member.domain.model.member.MemberSource;

public interface MemberSourceQuery {

	MemberSource execute(Long memberId);
}
