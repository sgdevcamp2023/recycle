package com.zzaug.member.domain.model.log;

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
public class LoginLog {

	private Long id;
	private Long memberId;
	private boolean isLogin;
	private String userAgent;
}
