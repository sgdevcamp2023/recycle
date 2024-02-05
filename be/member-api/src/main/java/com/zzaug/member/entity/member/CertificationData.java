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
public class CertificationData {

	private String certification;

	@Builder(toBuilder = true)
	public CertificationData(String certification) {
		this.certification = certification;
	}
}
