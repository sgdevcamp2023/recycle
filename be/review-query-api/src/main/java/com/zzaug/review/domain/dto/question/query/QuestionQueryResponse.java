package com.zzaug.review.domain.dto.question.query;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class QuestionQueryResponse {
	private Long question_id;
	private String content;
	private String author;
	private Long author_id;
	private int review_cnt;
	private LocalDateTime created_at;
	private LocalDateTime updated_at;
}
