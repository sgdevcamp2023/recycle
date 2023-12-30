package com.zzaug.security.authentication.token;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenAuthProvider implements AuthenticationProvider {

	private final TokenUserDetailsService tokenUserDetailsService;

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException, AccessDeniedException {

		String token =
				Optional.ofNullable(authentication.getPrincipal())
						.map(String.class::cast)
						.orElseThrow(() -> new IllegalArgumentException("token is missing"));

		UserDetails userDetails = tokenUserDetailsService.loadUserByUsername(token);

		SecurityContextHolder.getContext().setAuthentication(authentication);

		if (authentication instanceof PreAuthenticatedAuthenticationToken) {
			return new PreAuthenticatedAuthenticationToken(
					userDetails, userDetails.getPassword(), userDetails.getAuthorities());
		}
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return PreAuthenticatedAuthenticationToken.class.isAssignableFrom(authentication);
	}
}
