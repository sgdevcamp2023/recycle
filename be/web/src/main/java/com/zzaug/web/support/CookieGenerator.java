package com.zzaug.web.support;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

@Component
public class CookieGenerator {

	private static final Boolean HTTP_ONLY = true;
	private static final Boolean SECURE = true;

	@Value("${cookie.domain}")
	private String domain;

	@Value("${cookie.path}")
	private String path;

	@Value("${cookie.max-age}")
	private Integer maxAge;

	public ResponseCookie createCookie(CookieSameSite cookieSameSite, String key, String value) {
		return ResponseCookie.from(key, value)
				.sameSite(cookieSameSite.getValue())
				.domain(domain)
				.path(path)
				.maxAge(maxAge)
				.httpOnly(HTTP_ONLY)
				.secure(SECURE)
				.build();
	}
}
