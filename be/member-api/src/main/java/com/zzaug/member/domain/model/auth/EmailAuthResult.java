package com.zzaug.member.domain.model.auth;

import lombok.Getter;

@Getter
public enum EmailAuthResult {
	SUCCESS(true, "인증성공", "SUCCESS"),
	NOT_MATCH_CODE(false, "인증코드가 일치하지 않습니다.", "NOT_MATCH_CODE"),
	;

	private boolean success;
	private String description;
	private String reason;

	EmailAuthResult(boolean success, String description, String reason) {
		this.success = success;
		this.description = description;
		this.reason = reason;
	}

	public boolean isSuccess() {
		return success;
	}
}
