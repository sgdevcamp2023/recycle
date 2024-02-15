package com.zzaug.security.entity.log;

import com.zzaug.security.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Table(name = "invalid_access_token_log")
@SQLDelete(sql = "UPDATE invalid_access_token_log SET deleted=true where id=?")
public class InvalidTokenAccessEntity extends BaseEntity {

	@Lob
	@Column(name = "token", nullable = false)
	private String token;

	@Column(name = "ip", nullable = false)
	private String ip;

	@Column(name = "useragent", nullable = false)
	private String userAgent;

	@Builder.Default
	@Column(name = "resource", columnDefinition = "json")
	private String resource = "{}";
}
