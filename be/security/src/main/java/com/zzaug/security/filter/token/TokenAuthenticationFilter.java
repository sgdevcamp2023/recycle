package com.zzaug.security.filter.token;

import com.zzaug.security.exception.AccessTokenInvalidException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

@Slf4j
public class TokenAuthenticationFilter extends AbstractPreAuthenticatedProcessingFilter {

	private static final Pattern PATTERN_AUTHORIZATION_HEADER = Pattern.compile("^[Bb]earer (.*)$");

	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
		return resolveAccessToken(request);
	}

	@Override
	protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
		return resolveAccessToken(request);
	}

	private String resolveAccessToken(HttpServletRequest request) {
		String authorization = request.getHeader("Authorization");
		if (Objects.isNull(authorization)) {
			AccessTokenInvalidException exception =
					getAccessTokenInvalidException("Authorization header is missing");
			throw exception;
		}
		Matcher matcher = PATTERN_AUTHORIZATION_HEADER.matcher(authorization);
		if (!matcher.matches()) {
			AccessTokenInvalidException exception =
					getAccessTokenInvalidException("Authorization header is not a Bearer token");
			throw exception;
		}
		return matcher.group(1);
	}

	private AccessTokenInvalidException getAccessTokenInvalidException(String message) {
		AccessTokenInvalidException exception = new AccessTokenInvalidException(message);
		log.warn(exception.getMessage());
		return exception;
	}
}
