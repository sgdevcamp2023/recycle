package com.zzaug.member.entity.log;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public enum LoginStatus {
	LOGIN("로그인"),
	LOGOUT("로그아웃");
	private final String description;

	LoginStatus(String description) {
		this.description = description;
	}
}
