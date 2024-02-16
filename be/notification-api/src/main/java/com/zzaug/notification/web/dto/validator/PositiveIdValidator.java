package com.zzaug.notification.web.dto.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PositiveIdValidator implements ConstraintValidator<PositiveId, Long> {

	@Override
	public boolean isValid(Long value, ConstraintValidatorContext context) {
		return isOverZero(value);
	}

	private boolean isOverZero(Long value) {
		return value >= 0;
	}

	@Override
	public void initialize(PositiveId constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}
}
