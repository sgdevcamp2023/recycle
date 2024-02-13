package com.zzaug.member.domain.exception;

import org.apache.logging.log4j.util.Strings;

public class InvalidRequestException extends IllegalArgumentException {

	private final String param;

	public InvalidRequestException(String message, String param) {
		super(message);
		this.param = param;
	}

	public String getParam() {
		if (param == null) {
			return Strings.EMPTY;
		}
		return param;
	}
}
