package com.zzaug.member.domain.exception;

public class PasswordNotMatchException extends RuntimeException {

	public PasswordNotMatchException() {
		super("Password is not matched.");
	}
}
