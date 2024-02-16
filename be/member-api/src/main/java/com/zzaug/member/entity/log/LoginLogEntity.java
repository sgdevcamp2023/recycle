package com.zzaug.member.entity.log;

import com.zzaug.member.entity.MemberFKBaseEntity;
import javax.persistence.Column;
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
@Table(name = "login_log")
@SQLDelete(sql = "UPDATE login_log SET deleted=true where id=?")
public class LoginLogEntity extends MemberFKBaseEntity {

	@Column(name = "status", nullable = false)
	private LoginStatus status;

	@Column(name = "useragent", nullable = false)
	private String userAgent;
}
