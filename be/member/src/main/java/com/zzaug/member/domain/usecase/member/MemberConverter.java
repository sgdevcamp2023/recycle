package com.zzaug.member.domain.usecase.member;

import com.zzaug.member.domain.dto.member.MemberUseCaseRequest;
import com.zzaug.member.domain.model.member.Member;
import com.zzaug.member.entity.member.MemberEntity;
import org.springframework.stereotype.Component;

@Component
public class MemberConverter {

	public Member from(MemberUseCaseRequest source) {
		return Member.builder().name(source.getName()).build();
	}

	public Member from(MemberEntity source) {
		return Member.builder().name(source.getId().toString()).build();
	}
}
