package com.zzaug.review.domain.dto.question.query;

import java.sql.Timestamp;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class QuestionTempQueryResponse {
	private String t_id;
	private String content;
	private String author;
	private Long author_id;
	private Timestamp created_at;
}
