package com.zzaug.security.token;

import com.zzaug.security.authentication.authority.Roles;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class TokenGenerator {

	@Value("${security.jwt.token.secretkey}")
	private String secretKey;

	@Value("${security.jwt.token.validtime.access}")
	private Long accessTokenValidTime;

	@Value("${security.jwt.token.validtime.refresh}")
	private Long refreshTokenValidTime;

	private static final String MEMBER_ID_CLAIM_KEY = "memberId";
	private static final String MEMBER_CERTIFICATION_CLAIM_KEY = "certification";
	public static final String MEMBER_EMAIL_CLAIM_KEY = "email";
	public static final String MEMBER_GITHUB_CLAIM_KEY = "github";
	private static final String MEMBER_ROLE_CLAIM_KEY = "memberRole";

	public AuthToken generateAuthToken(
			Long memberId, List<Roles> memberRoles, String certification, String email, String github) {
		return AuthToken.builder()
				.accessToken(generateAccessToken(memberId, memberRoles, certification, email, github))
				.refreshToken(generateRefreshToken(memberId, memberRoles, certification, email, github))
				.build();
	}

	public String generateAccessToken(
			Long memberId, List<Roles> memberRoles, String certification, String email, String github) {
		Date now = new Date();
		List<String> roles = convertToStringList(memberRoles);

		return Jwts.builder()
				.setHeaderParam(Header.TYPE, Header.JWT_TYPE)
				.claim(MEMBER_ID_CLAIM_KEY, memberId)
				.claim(MEMBER_CERTIFICATION_CLAIM_KEY, certification)
				.claim(MEMBER_ROLE_CLAIM_KEY, roles.toString())
				.claim(MEMBER_EMAIL_CLAIM_KEY, email)
				.claim(MEMBER_GITHUB_CLAIM_KEY, github)
				.setIssuedAt(now)
				.setExpiration(new Date(now.getTime() + accessTokenValidTime))
				.signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
				.compact();
	}

	String generateRefreshToken(
			Long memberId, List<Roles> memberRoles, String certification, String email, String github) {
		Date now = new Date();
		List<String> roles = convertToStringList(memberRoles);

		return Jwts.builder()
				.setHeaderParam(Header.TYPE, Header.JWT_TYPE)
				.claim(MEMBER_ID_CLAIM_KEY, memberId)
				.claim(MEMBER_CERTIFICATION_CLAIM_KEY, certification)
				.claim(MEMBER_ROLE_CLAIM_KEY, roles.toString())
				.claim(MEMBER_EMAIL_CLAIM_KEY, email)
				.claim(MEMBER_GITHUB_CLAIM_KEY, github)
				.setIssuedAt(now)
				.setExpiration(new Date(now.getTime() + refreshTokenValidTime))
				.signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
				.compact();
	}

	private List<String> convertToStringList(List<Roles> memberRoles) {
		List<String> stringRoles = new ArrayList<>();
		for (Roles role : memberRoles) {
			stringRoles.add(role.getRole());
		}
		return stringRoles;
	}
}
