package com.zzaug.member.web.support.usecase;

import com.zzaug.member.domain.dto.member.MemberUseCaseRequest;
import com.zzaug.member.web.dto.member.MemberRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MemberUseCaseRequestConverter {

	public static MemberUseCaseRequest from(MemberRequest request) {
		return MemberUseCaseRequest.builder().name(request.getName()).build();
	}
}
