package com.zzaug.member.web.dto.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CertificationValidator implements ConstraintValidator<Certification, String> {
	private static final String ENGLISH_AND_NUMBER_REGEX = "^[a-zA-Z0-9]*$";
	private static final Integer MIN_LENGTH = 4;
	private static final Integer MAX_LENGTH = 16;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) {
			return false;
		}

		if (value.length() < MIN_LENGTH) {
			return false;
		}

		if (value.length() > MAX_LENGTH) {
			return false;
		}

		if (!isValidCertification(value)) {
			return false;
		}

		return true;
	}

	private boolean isValidCertification(String certification) {
		Pattern pattern = Pattern.compile(ENGLISH_AND_NUMBER_REGEX);
		Matcher matcher = pattern.matcher(certification);
		return matcher.matches();
	}

	@Override
	public void initialize(Certification constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}
}
