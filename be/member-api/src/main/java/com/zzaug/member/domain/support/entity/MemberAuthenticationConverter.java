package com.zzaug.member.domain.support.entity;

import com.zzaug.member.domain.model.member.MemberAuthentication;
import com.zzaug.member.entity.member.AuthenticationEntity;
import com.zzaug.member.entity.member.CertificationData;
import com.zzaug.member.entity.member.PasswordData;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MemberAuthenticationConverter {

	public static MemberAuthentication from(AuthenticationEntity entity) {
		return MemberAuthentication.builder()
				.id(entity.getId())
				.memberId(entity.getMemberId())
				.certification(entity.getCertification().getCertification())
				.password(entity.getPassword().getPassword())
				.build();
	}

	public static AuthenticationEntity to(MemberAuthentication source) {
		return AuthenticationEntity.builder()
				.id(source.getId())
				.memberId(source.getMemberId())
				.certification(CertificationData.builder().certification(source.getCertification()).build())
				.password(PasswordData.builder().password(source.getPassword()).build())
				.build();
	}
}
