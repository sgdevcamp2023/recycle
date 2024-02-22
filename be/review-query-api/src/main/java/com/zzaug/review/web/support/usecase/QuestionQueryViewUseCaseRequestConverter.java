package com.zzaug.review.web.support.usecase;

import com.zzaug.review.domain.dto.question.query.QuestionQueryViewUseCaseRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class QuestionQueryViewUseCaseRequestConverter {
	public static QuestionQueryViewUseCaseRequest from(Long questionId) {
		return QuestionQueryViewUseCaseRequest.builder().questionId(questionId).build();
	}
}
