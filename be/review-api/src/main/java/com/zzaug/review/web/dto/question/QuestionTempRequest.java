package com.zzaug.review.web.dto.question;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class QuestionTempRequest {
	@NotBlank private String tempId;
	@NotEmpty private String content;
}
