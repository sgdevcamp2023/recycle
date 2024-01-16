package com.zzaug.review.web.dto.question;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class QuestionTempRequest {
	private String t_id;
	private String content;
}
