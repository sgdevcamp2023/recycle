package com.zzaug.review.web.support.usecase;

import com.zzaug.review.domain.dto.review.query.ReviewByQuestionUseCaseRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ReviewByQuestionUseCaseRequestConverter {
	public static ReviewByQuestionUseCaseRequest from(Long questionId) {
		return ReviewByQuestionUseCaseRequest.builder().questionId(questionId).build();
	}
}
