package com.zzaug.review.web.dto.question;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class QuestionViewRequest {
	private Long questionId;
}
