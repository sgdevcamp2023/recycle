package com.zzaug.review.domain.dto.question;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class QuestionTempCreateUseCaseRequest {
	private String t_id;
	private String content;
	private String author;
	private Long author_id;
	private int review_cnt;
	private LocalDateTime created_at;
	private LocalDateTime updated_at;
}
