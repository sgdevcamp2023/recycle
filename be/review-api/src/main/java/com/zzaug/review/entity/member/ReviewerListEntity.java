package com.zzaug.review.entity.member;

import javax.persistence.*;
import lombok.*;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ReviewerListEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reviewer_list_id")
	private Long reviewerListId;

	@Column(name = "reviewer_name")
	private String reviewerName;

	@Column(name = "reviewer_id")
	private Long reviewerId;

	@Column(name = "question_id")
	private Long questionId;
}
