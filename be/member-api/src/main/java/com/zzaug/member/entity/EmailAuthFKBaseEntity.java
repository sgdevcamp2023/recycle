package com.zzaug.member.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@MappedSuperclass
@SuperBuilder(toBuilder = true)
public abstract class EmailAuthFKBaseEntity extends BaseEntity {
	@Column(name = "email_auth_fk", nullable = false)
	private Long emailAuthId;
}
