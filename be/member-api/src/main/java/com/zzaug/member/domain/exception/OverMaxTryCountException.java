package com.zzaug.member.domain.exception;

public class OverMaxTryCountException extends RuntimeException {

	public OverMaxTryCountException(String message) {
		super(message);
	}
}
