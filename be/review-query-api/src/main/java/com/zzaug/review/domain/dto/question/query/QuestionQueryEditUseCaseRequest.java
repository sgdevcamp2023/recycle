package com.zzaug.review.domain.dto.question.query;

import java.time.LocalDateTime;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Setter
public class QuestionQueryEditUseCaseRequest {
	private Long questionId;
	private String content;
	private String author;
	private Long authorId;
	private int reviewCnt;
	private LocalDateTime createdAt;
	private LocalDateTime deletedAt;
}
