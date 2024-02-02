package com.zzaug.review.web.support.usecase;

import com.zzaug.review.domain.dto.question.QuestionTempViewUseCaseRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class QuestionTempViewUseCaseRequestConverter {
	public static QuestionTempViewUseCaseRequest from(String tempId, Long authorId) {
		return QuestionTempViewUseCaseRequest.builder().tempId(tempId).authorId(authorId).build();
	}

	public static QuestionTempViewUseCaseRequest from(Long authorId) {
		return QuestionTempViewUseCaseRequest.builder().authorId(authorId).build();
	}
}
