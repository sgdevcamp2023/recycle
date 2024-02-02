package com.zzaug.review.domain.dto.question;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class QuestionDeleteUseCaseRequest {
	private Long questionId;
	private Long authorId;
}
