package com.zzaug.member.entity.member;

import com.zzaug.member.entity.MemberFKBaseEntity;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;

@Entity
@Getter
@ToString(callSuper = true)
@SuperBuilder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "member_authentication")
@SQLDelete(sql = "UPDATE member_authentication SET deleted=true where id=?")
public class AuthenticationEntity extends MemberFKBaseEntity {

	@Convert(converter = CertificationDataConverter.class)
	@Column(name = "certification", nullable = false)
	private CertificationData certification;

	@Convert(converter = PasswordDataConverter.class)
	@Column(name = "password", nullable = false)
	private PasswordData password;
}
