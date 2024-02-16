package com.zzaug.security.entity.auth;

import com.zzaug.security.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
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
@Table(name = "black_token_auth")
@SQLDelete(sql = "UPDATE black_token_auth SET deleted=true where id=?")
public class BlackTokenAuthEntity extends BaseEntity {

	@Lob
	@Convert(converter = TokenDataConverter.class)
	@Column(name = "token", nullable = false)
	private TokenData token;

	@Enumerated(EnumType.STRING)
	@Column(name = "token_type", nullable = false)
	private TokenType tokenType;
}
