package com.zzaug.notification.entity.notification;

import com.zzaug.notification.entity.BaseEntity;
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
@Table(name = "request_review_history")
@SQLDelete(sql = "UPDATE request_review_history SET deleted=true where id=?")
public class RequestReviewHistoryEntity extends BaseEntity {

	@Column(name = "member_id", nullable = false)
	private Long memberId;

	@Column(name = "question_id", nullable = false)
	private Long questionId;

	@Column(name = "request_member_id", nullable = false)
	private Long requestMemberId;
}
