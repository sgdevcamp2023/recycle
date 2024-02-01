package com.zzaug.review.support;

public enum MessageCode {
	SUCCESS("success", "성공"),
	RESOURCE_DELETED("resource.deleted", "삭제되었습니다."),
	RESOURCE_CREATED("resource.created", "새로 생성되었습니다."),
	RESOURCE_NOT_FOUND("resource.not.found", "해당 리소스를 찾을 수 없습니다."),
	ACCESS_DENIED("access.denied", "접근 권한이 없습니다."),
	RESOURCE_MODIFIED("resource.modified", "수정되었습니다.");

	private final String code;
	private final String value;

	MessageCode(String code, String value) {
		this.code = code;
		this.value = value;
	}

	public String getCode() {
		return this.code;
	}

	public String getValue() {
		return this.value;
	}
}
