package com.zzaug.member.domain.exception;

import lombok.Getter;

@Getter
public enum DBSource {
	MEMBER("Member"),
	AUTHENTICATION("Authentication"),
	EXTERNAL_CONTACT("ExternalContact"),
	EMAIL_AUTH_LOG("EmailAuthLog"),
	INVALID_TOKEN_ACCESS("InvalidTokenAccess"),
	LOGIN_LOG("LoginLog"),
	BLACK_TOKEN_AUTH("BlackTokenAuth"),
	EMAIL_AUTH("EmailAuth");

	private String type;

	DBSource(String type) {
		this.type = type;
	}
}
