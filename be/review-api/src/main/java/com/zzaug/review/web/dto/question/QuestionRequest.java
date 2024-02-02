package com.zzaug.review.web.dto.question;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class QuestionRequest {
	@NotEmpty
	private String content;
}
