package com.zzaug.member.web.dto.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Certification, String> {
	private static final String ENGLISH_AND_NUMBER_AND_SPECIAL_REGEX =
			"^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]$";
	private static final Integer MIN_LENGTH = 8;
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

		if (!isValidPassword(value)) {
			return false;
		}

		return true;
	}

	private boolean isValidPassword(String password) {
		Pattern pattern = Pattern.compile(ENGLISH_AND_NUMBER_AND_SPECIAL_REGEX);
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();
	}

	@Override
	public void initialize(Certification constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}
}
