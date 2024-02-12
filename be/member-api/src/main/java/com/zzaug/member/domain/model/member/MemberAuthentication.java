package com.zzaug.member.domain.model.member;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class MemberAuthentication {

	private Long id;
	private Long memberId;
	private String certification;
	private String password;

	public boolean isSameCertification(String certification) {
		return Objects.equals(this.certification, certification);
	}

	public void updateCertification(String certification) {
		this.certification = certification;
	}

	public boolean isMatchPassword(PasswordEncoder passwordEncoder, String password) {
		return passwordEncoder.matches(password, this.password);
	}
}
