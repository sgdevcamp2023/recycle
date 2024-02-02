package com.zzaug.review.domain.dto.review;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ReviewDeleteUseCaseRequest {
	private Long reviewId;
	private Long questionId;
	private Long authorId;
}
