package com.zzaug.review.web.dto.question;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class QuestionDeleteRequest {
	private Long questionId;
	private Long authorId;
}
