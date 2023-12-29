package com.zzaug.member.domain.support.entity;

import com.zzaug.member.domain.model.member.Member;
import com.zzaug.member.entity.member.MemberEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MemberEntityConverter {

	public static MemberEntity from(Member source) {
		return MemberEntity.builder().build();
	}
}
