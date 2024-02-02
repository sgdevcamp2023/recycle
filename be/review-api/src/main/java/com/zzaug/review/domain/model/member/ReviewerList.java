package com.zzaug.review.domain.model.member;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ReviewerList {
	private Long reviewerListId;

	private String reviewerName;

	private Long reviewerId;

	private Long questionId;
}
