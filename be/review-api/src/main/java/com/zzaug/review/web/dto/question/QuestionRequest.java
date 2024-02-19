package com.zzaug.review.web.dto.question;

import javax.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class QuestionRequest {
	@NotEmpty private String content;
}
