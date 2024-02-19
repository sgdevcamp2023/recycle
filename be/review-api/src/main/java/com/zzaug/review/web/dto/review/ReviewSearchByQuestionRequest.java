package com.zzaug.review.web.dto.review;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ReviewSearchByQuestionRequest {
	private Long authorId;
	private String query;
}
