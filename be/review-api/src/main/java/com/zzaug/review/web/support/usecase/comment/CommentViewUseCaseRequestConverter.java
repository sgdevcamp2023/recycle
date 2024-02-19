package com.zzaug.review.web.support.usecase.comment;

import com.zzaug.review.domain.dto.comment.CommentViewUseCaseRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CommentViewUseCaseRequestConverter {
	public static CommentViewUseCaseRequest from(Long quesionId) {
		return CommentViewUseCaseRequest.builder().questionId(quesionId).build();
	}
}
