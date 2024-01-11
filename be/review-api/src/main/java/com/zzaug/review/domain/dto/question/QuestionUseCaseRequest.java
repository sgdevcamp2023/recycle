package com.zzaug.review.domain.dto.question;

import java.sql.Timestamp;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class QuestionUseCaseRequest {
	private Long question_id;
	private String content;
	private String author;
	private Long author_id;
	private int review_cnt;
	private Timestamp created_at;
	private Timestamp updated_at;
}
