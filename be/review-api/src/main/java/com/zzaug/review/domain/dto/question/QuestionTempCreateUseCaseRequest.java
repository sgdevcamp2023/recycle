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
	private String tempId;
	private String content;
	private String author;
	private Long authorId;
	private LocalDateTime createdAt;
}
