package com.zzaug.member.entity.member;

import com.zzaug.member.entity.BaseEntity;
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
@Table(name = "member")
@SQLDelete(sql = "UPDATE member SET deleted=true where id=?")
public class MemberEntity extends BaseEntity {

	@Enumerated(EnumType.STRING)
	@Builder.Default
	@Column(name = "member_st", nullable = false)
	private MemberStatus status = MemberStatus.REGULAR;

	@Builder.Default
	@Column(name = "resource", columnDefinition = "json")
	private String resource = "{}";
}
