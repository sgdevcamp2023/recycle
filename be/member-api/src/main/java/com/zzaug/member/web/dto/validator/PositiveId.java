package com.zzaug.member.web.dto.validator;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({PARAMETER, FIELD})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = PositiveIdValidator.class)
public @interface PositiveId {
	String message() default "Id must be positive.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
