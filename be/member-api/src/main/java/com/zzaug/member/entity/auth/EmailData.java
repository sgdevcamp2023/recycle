package com.zzaug.member.entity.auth;

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
public class EmailData {

	private String email;

	@Builder(toBuilder = true)
	public EmailData(String email) {
		validate(email);
		this.email = email;
	}

	private void validate(String email) {
		if (email == null || email.isEmpty()) {
			throw new IllegalArgumentException("email is null or empty");
		}
		if (!isValidEmail(email)) {
			throw new IllegalArgumentException("email is not valid");
		}
	}

	private static final String EMAIL_REGEX =
			"^[a-zA-Z0-9_+&*-]+(?:\\."
					+ "[a-zA-Z0-9_+&*-]+)*@"
					+ "(?:[a-zA-Z0-9-]+\\.)+[a-z"
					+ "A-Z]{2,7}$";

	public static boolean isValidEmail(String email) {
		Pattern pattern = Pattern.compile(EMAIL_REGEX);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
}
