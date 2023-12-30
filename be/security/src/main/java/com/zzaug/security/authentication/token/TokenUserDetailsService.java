package com.zzaug.security.authentication.token;

import com.zzaug.security.authentication.authority.Roles;
import com.zzaug.security.exception.AccessTokenInvalidException;
import com.zzaug.security.token.TokenResolver;
import io.jsonwebtoken.Claims;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenUserDetailsService implements UserDetailsService {

	private static final String MEMBER_ID_CLAIM_KEY = "memberId";
	private static final String MEMBER_ROLE_CLAIM_KEY = "memberRole";

	private final TokenResolver tokenResolver;

	@Override
	public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {

		Claims claims =
				tokenResolver
						.resolve(token)
						.orElseThrow(
								() ->
										new AccessTokenInvalidException("Invalid access token. accessToken: " + token));

		Long id = claims.get(MEMBER_ID_CLAIM_KEY, Long.class);
		String roles = claims.get(MEMBER_ROLE_CLAIM_KEY, String.class);

		List<GrantedAuthority> authorities = toAuthorities(roles);

		return TokenUserDetails.builder().id(String.valueOf(id)).authorities(authorities).build();
	}

	private static List<GrantedAuthority> toAuthorities(String roles) {
		String[] tokens = StringUtils.splitPreserveAllTokens(roles, "[,]");
		List<GrantedAuthority> rtn = new ArrayList<>();
		for (String token : tokens) {
			if (!Objects.equals(token, "")) {
				String role = token.trim();
				try {
					rtn.add(Roles.valueOf(role).getAuthority());
				} catch (IllegalArgumentException exception) {
					log.error("Invalid role. role: {}", role);
					// todo 알림 기능 추가
				}
			}
		}
		return rtn;
	}
}
