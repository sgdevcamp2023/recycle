package com.zzaug.review.domain.dto.comment;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CommentViewUseCaseRequest {
	private Long questionId;
}
