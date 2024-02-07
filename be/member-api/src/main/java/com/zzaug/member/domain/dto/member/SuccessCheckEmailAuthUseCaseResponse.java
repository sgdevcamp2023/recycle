package com.zzaug.member.domain.dto.member;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class SuccessCheckEmailAuthUseCaseResponse extends CheckEmailAuthUseCaseResponse {

	private String accessToken;
	private String refreshToken;
}
