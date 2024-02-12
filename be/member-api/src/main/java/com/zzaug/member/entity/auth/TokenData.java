// package com.zzaug.member.entity.auth;
//
// import lombok.Builder;
// import lombok.EqualsAndHashCode;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.ToString;
//
// @Getter
// @ToString
// @EqualsAndHashCode
// @NoArgsConstructor
// public class TokenData {
//
//	private String token;
//
//	@Builder(toBuilder = true)
//	public TokenData(String token) {
//		validate(token);
//		this.token = token;
//	}
//
//	private void validate(String token) {
//		if (token == null || token.isEmpty()) {
//			throw new IllegalArgumentException("token is null or empty");
//		}
//		String[] splitValues = token.split("\\.");
//		if (splitValues.length != 3) {
//			throw new IllegalArgumentException("token is invalid");
//		}
//	}
// }
