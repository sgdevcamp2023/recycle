package com.zzaug.review.domain.dto.review;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ReviewTempViewUseCaseRequest {
	private String tempId;
	private Long authorId;
	private Long questionId;
}
