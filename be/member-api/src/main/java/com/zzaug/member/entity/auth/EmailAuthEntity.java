package com.zzaug.member.entity.auth;

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
@Table(name = "email_auth")
@SQLDelete(sql = "UPDATE email_auth SET deleted=true where id=?")
public class EmailAuthEntity extends MemberFKBaseEntity {

	@Convert(converter = EmailDataConverter.class)
	@Column(name = "email", nullable = false)
	private EmailData email;

	@Column(name = "nonce", nullable = false)
	private String nonce;

	@Column(name = "code", nullable = false)
	private String code;
}
