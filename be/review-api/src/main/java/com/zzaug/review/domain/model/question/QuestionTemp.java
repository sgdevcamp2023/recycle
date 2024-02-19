package com.zzaug.review.domain.model.question;

import java.time.LocalDateTime;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class QuestionTemp {

	private String tempId;

	private String content;

	private String author;

	private Long authorId;

	private LocalDateTime createdAt;
}
