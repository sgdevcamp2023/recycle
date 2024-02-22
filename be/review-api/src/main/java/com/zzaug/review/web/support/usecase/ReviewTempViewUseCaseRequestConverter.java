package com.zzaug.review.web.support.usecase;

import com.zzaug.review.domain.dto.review.ReviewTempViewUseCaseRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ReviewTempViewUseCaseRequestConverter {
	public static ReviewTempViewUseCaseRequest from(String tempId) {
		return ReviewTempViewUseCaseRequest.builder().tempId(tempId).build();
	}

	public static ReviewTempViewUseCaseRequest from(Long authorId, Long questionId) {
		return ReviewTempViewUseCaseRequest.builder().authorId(authorId).questionId(questionId).build();
	}
}
