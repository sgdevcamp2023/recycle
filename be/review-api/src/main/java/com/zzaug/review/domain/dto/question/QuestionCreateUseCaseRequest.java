package com.zzaug.review.domain.dto.question;

import java.time.LocalDateTime;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class QuestionCreateUseCaseRequest {
	private Long questionId;
	private String content;
	private String author;
	private Long authorId;
	private int reviewCnt;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
