package com.zzaug.member.persistence.support.checker;

import java.lang.reflect.Method;

public class RepositoryQueryMethodAnnotationMissingException extends RuntimeException {

	public RepositoryQueryMethodAnnotationMissingException(Method method) {
		super(method.getName() + " have query but not have annotation");
	}
}
