package com.zzaug.review.domain.dto.review;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class SearchByQuestionUseCaseRequest {
	private Long authorId;
	private String query;
}
