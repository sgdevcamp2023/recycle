package com.zzaug.review.web.dto.question;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class QuestionTempRequest {
	@NotBlank
	private String tempId;
	@NotEmpty
	private String content;
}
