package com.zzaug.member.domain.exception;

public class SourceNotFoundException extends IllegalArgumentException {

	public SourceNotFoundException(DBSource target, Long id) {
		super(String.format("%s not found. (id: %d)", target.getType(), id));
	}

	public SourceNotFoundException(DBSource target, String by, String id) {
		super(String.format("%s not found. (by: %s, id: %s)", target.getType(), by, id));
	}

	public SourceNotFoundException(DBSource target, String by, Long id) {
		super(String.format("%s not found. (by: %s, id: %s)", target.getType(), by, id));
	}
}
