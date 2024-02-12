package com.zzaug.security.authentication.token;

import com.zzaug.security.authentication.authority.Roles;
import com.zzaug.security.entity.auth.TokenData;
import com.zzaug.security.exception.AccessTokenInvalidException;
import com.zzaug.security.persistence.auth.BlackTokenAuthRepository;
import com.zzaug.security.persistence.transaction.SecurityTransactional;
import com.zzaug.security.redis.auth.BlackAuthTokenHash;
import com.zzaug.security.redis.auth.BlackAuthTokenHashRepository;
import com.zzaug.security.redis.auth.WhiteAuthTokenHash;
import com.zzaug.security.redis.auth.WhiteAuthTokenHashRepository;
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
	private static final String MEMBER_CERTIFICATION_CLAIM_KEY = "certification";
	private static final String MEMBER_ROLE_CLAIM_KEY = "memberRole";

	private final TokenResolver tokenResolver;

	private final BlackTokenAuthRepository blackTokenAuthRepository;

	// cache
	private final WhiteAuthTokenHashRepository whiteAuthTokenHashRepository;
	private final BlackAuthTokenHashRepository blackAuthTokenHashRepository;

	@Override
	@SecurityTransactional
	public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {
		boolean isWhite = isWhiteToken(token);
		if (!isWhite) {
			isValidToken(token);
			log.debug("Save token to WhiteList. \ntoken: {}", token);
			whiteAuthTokenHashRepository.save(WhiteAuthTokenHash.builder().token(token).build());
		}

		Claims claims =
				tokenResolver
						.resolve(token)
						.orElseThrow(
								() ->
										new AccessTokenInvalidException("Invalid access token. accessToken: " + token));

		Long id = claims.get(MEMBER_ID_CLAIM_KEY, Long.class);
		String certification = claims.get(MEMBER_CERTIFICATION_CLAIM_KEY, String.class);
		String roles = claims.get(MEMBER_ROLE_CLAIM_KEY, String.class);

		List<GrantedAuthority> authorities = toAuthorities(roles);

		return TokenUserDetails.builder()
				.id(String.valueOf(id))
				.certification(certification)
				.authorities(authorities)
				.build();
	}

	private boolean isWhiteToken(String token) {
		log.debug("Check token. \ntoken: {}", token);
		return whiteAuthTokenHashRepository.existsByToken(token);
	}

	private void isValidToken(String token) {
		log.debug("Check token on BlackList. \ntoken: {}", token);
		boolean isOnCache = blackAuthTokenHashRepository.existsByToken(token);
		if (isOnCache) {
			log.warn("Token is onn BlackList \n{}", token);
			throw new AccessTokenInvalidException("Invalid access token. accessToken: " + token);
		}
		boolean isOnDB =
				blackTokenAuthRepository.existsByTokenAndDeletedFalse(
						TokenData.builder().token(token).build());
		if (isOnDB) {
			log.warn("Token is onn BlackList \n{}", token);
			blackAuthTokenHashRepository.save(BlackAuthTokenHash.builder().token(token).build());
			throw new AccessTokenInvalidException("Invalid access token. accessToken: " + token);
		}
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
