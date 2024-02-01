package com.zzaug.member.domain.exception;

public class ExistSourceException extends RuntimeException {

	public ExistSourceException(DBSource target, Long id) {
		super(String.format("%s id: %d is exist", target.getType(), id));
	}

	public ExistSourceException(DBSource target, String value) {
		super(String.format("%s value: %s is exist", target.getType(), value));
	}
}
