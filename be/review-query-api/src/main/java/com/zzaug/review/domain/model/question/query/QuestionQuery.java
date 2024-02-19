package com.zzaug.review.domain.model.question.query;

import java.time.LocalDateTime;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class QuestionQuery {
	private Long questionId;
	private String content;
	private String author;
	private Long authorId;
	private int reviewCnt;
	private LocalDateTime createdAt;
	private LocalDateTime deletedAt;
	private boolean isDeleted;
}
