package com.zzaug.member.entity.member;

import com.zzaug.member.entity.MemberFKBaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "member_external_contact")
@SQLDelete(sql = "UPDATE member_external_contact SET deleted=true where id=?")
public class ExternalContactEntity extends MemberFKBaseEntity {

	@Enumerated(EnumType.STRING)
	@Column(name = "contact_tp", nullable = false)
	private ContactType contactType;

	@Column(name = "source", nullable = false)
	private String source;

	@Builder.Default
	@Column(name = "resource", columnDefinition = "json")
	private String resource = "{}";
}
