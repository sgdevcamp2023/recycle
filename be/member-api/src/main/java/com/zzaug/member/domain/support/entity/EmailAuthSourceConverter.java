package com.zzaug.member.domain.support.entity;

import com.zzaug.member.domain.model.auth.EmailAuthSource;
import com.zzaug.member.entity.auth.EmailAuthEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EmailAuthSourceConverter {

	public static EmailAuthSource from(EmailAuthEntity entity) {
		return EmailAuthSource.builder()
				.id(entity.getId())
				.memberId(entity.getMemberId())
				.email(entity.getEmail().getEmail())
				.nonce(entity.getNonce())
				.code(entity.getCode())
				.build();
	}
}
