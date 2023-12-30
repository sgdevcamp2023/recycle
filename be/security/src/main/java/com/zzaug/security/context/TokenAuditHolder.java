package com.zzaug.security.context;

import com.zzaug.security.authentication.token.TokenUserDetails;
import java.util.List;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@UtilityClass
public class TokenAuditHolder {

	private static final String NOT_USE_ID_VALUE = "0";
	private static final GrantedAuthority NOT_USE_AUTHORITY = new SimpleGrantedAuthority("NOT_USE");

	public static UserDetails get() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null
				|| !authentication.isAuthenticated()
				|| authentication.getPrincipal().equals("anonymousUser")) {
			return TokenUserDetails.builder()
					.id(NOT_USE_ID_VALUE)
					.authorities(List.of(NOT_USE_AUTHORITY))
					.build();
		}

		return (TokenUserDetails) authentication.getPrincipal();
	}
}
