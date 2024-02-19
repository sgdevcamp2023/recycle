package com.zzaug.review.domain.dto.question;

import lombok.*;
import org.springframework.data.domain.PageRequest;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class QuestionSearchUseCaseRequest {
	private Long authorId;
	private String query;
	private PageRequest pageRequest;
}
