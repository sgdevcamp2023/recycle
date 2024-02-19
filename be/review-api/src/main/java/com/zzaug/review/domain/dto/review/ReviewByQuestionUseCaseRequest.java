package com.zzaug.review.domain.dto.review;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ReviewByQuestionUseCaseRequest {

	private Long questionId;
}
