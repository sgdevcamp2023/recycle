package com.zzaug.member.domain.model.auth;

import java.util.Objects;
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
public class EmailAuthSource {

	private Long id;
	private Long memberId;

	private String email;
	private String nonce;
	private String code;

	public boolean isMemberId(Long memberId) {
		return Objects.equals(this.memberId, memberId);
	}

	public boolean isNonce(String nonce) {
		return Objects.equals(this.nonce, nonce);
	}

	public boolean isCode(String code) {
		return Objects.equals(this.code, code);
	}
}
