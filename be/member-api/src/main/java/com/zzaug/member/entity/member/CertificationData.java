package com.zzaug.member.entity.member;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class CertificationData {

	private String certification;

	@Builder(toBuilder = true)
	public CertificationData(String certification) {
		validate(certification);
		this.certification = certification;
	}

	private void validate(String certification) {
		if (certification == null || certification.isEmpty()) {
			throw new IllegalArgumentException("certification is null or empty");
		}
		if (!isValidCertification(certification)) {
			throw new IllegalArgumentException("certification is not valid");
		}
	}

	private static final String ENGLISH_AND_NUMBER_REGEX = "^[a-zA-Z0-9]*$";

	private boolean isValidCertification(String certification) {
		Pattern pattern = Pattern.compile(ENGLISH_AND_NUMBER_REGEX);
		Matcher matcher = pattern.matcher(certification);
		return matcher.matches();
	}
}
