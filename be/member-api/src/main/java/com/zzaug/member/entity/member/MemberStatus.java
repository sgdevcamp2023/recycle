package com.zzaug.member.entity.member;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum MemberStatus {
	REGULAR("정회원"),
	ASSOCIATE("준회원"),
	SEPARATE("장기미이용 회원"),
	WITHDRAWN("탈퇴회원"),
	SUSPENDED("정지회원"),
	;

	private final String description;

	MemberStatus(String description) {
		this.description = description;
	}
}
