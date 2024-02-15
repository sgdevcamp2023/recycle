package com.zzaug.member.domain.dto.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class MemberAuthToken {

	private Long memberId;
	private String accessToken;
	private String refreshToken;

	public AccessTokenResponse toResponse() {
		return new AccessTokenResponse(this.accessToken);
	}
}
