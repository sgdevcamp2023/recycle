package com.zzaug.member.entity.member;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class PasswordData {

	private String password;

	@Builder(toBuilder = true)
	public PasswordData(String password) {
		this.password = password;
	}
}
