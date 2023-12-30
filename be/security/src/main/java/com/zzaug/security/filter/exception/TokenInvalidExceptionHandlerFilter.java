package com.zzaug.security.filter.exception;

import com.zzaug.security.exception.AccessTokenInvalidException;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

public class TokenInvalidExceptionHandlerFilter extends OncePerRequestFilter {

	private static final String CONTENT_TYPE = "application/json; charset=UTF-8";
	private static final ErrorResponse ERROR_RESPONSE = new ErrorResponse();

	@Override
	protected void doFilterInternal(
			HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			filterChain.doFilter(request, response);
		} catch (AccessTokenInvalidException e) {
			setError(response, e);
		}
	}

	private void setError(HttpServletResponse response, Exception e) throws IOException {
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		response.setContentType(CONTENT_TYPE);
		response.getWriter().write(ERROR_RESPONSE.toString());
	}

	private static class ErrorResponse {
		private String code = "fail.authentication";
		private String message = "인증이 필요해요.";

		@Override
		public String toString() {
			return "{" + "\"code\" : \"" + code + "\"" + ", \"message\" : \"" + message + "\"}";
		}
	}
}
