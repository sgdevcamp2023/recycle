package com.zzaug.member.domain.exception;

public class SourceNotFoundException extends IllegalArgumentException {

	public SourceNotFoundException(DBSource target, Long id) {
		super(String.format("%s not found. (id: %d)", target.getType(), id));
	}
}
