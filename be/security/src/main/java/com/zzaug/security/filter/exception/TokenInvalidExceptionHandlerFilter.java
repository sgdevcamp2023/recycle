package com.zzaug.security.filter.exception;

import com.zzaug.security.event.InvalidTokenAccessEvent;
import com.zzaug.security.exception.AccessTokenInvalidException;
import com.zzaug.security.filter.token.AccessTokenResolver;
import java.io.IOException;
import java.time.LocalDateTime;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
public class TokenInvalidExceptionHandlerFilter extends OncePerRequestFilter {

	private static final String CONTENT_TYPE = "application/json; charset=UTF-8";
	private final ApplicationEventPublisher applicationEventPublisher;

	@Override
	protected void doFilterInternal(
			HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			filterChain.doFilter(request, response);
		} catch (AccessTokenInvalidException e) {
			setError(response, e);
			publishEvent(request);
		}
	}

	private void publishEvent(HttpServletRequest request) {
		String token = resolveAccessToken(request);
		String ip = request.getRemoteAddr();
		String userAgent = request.getHeader("User-Agent");
		applicationEventPublisher.publishEvent(
				InvalidTokenAccessEvent.builder().token(token).ip(ip).userAgent(userAgent).build());
	}

	private String resolveAccessToken(HttpServletRequest request) {
		String authorization = request.getHeader("Authorization");
		return AccessTokenResolver.resolve(authorization);
	}

	private void setError(HttpServletResponse response, Exception e) throws IOException {
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		response.setContentType(CONTENT_TYPE);
		ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now());
		response.getWriter().write(errorResponse.toString());
	}

	@RequiredArgsConstructor
	private static class ErrorResponse {
		private static String code = "fail.authentication";
		private static String message = "인증이 필요해요.";
		private final LocalDateTime timestamp;

		@Override
		public String toString() {
			return "{"
					+ "\"code\" : \""
					+ code
					+ "\""
					+ ", \"message\" : \""
					+ message
					+ "\""
					+ ", \"timestamp\" : \""
					+ timestamp
					+ "\"}";
		}
	}
}
