package com.zzaug.review.web.support.usecase;

import com.zzaug.review.domain.dto.review.ReviewUseCaseRequest;
import com.zzaug.review.web.dto.review.ReviewRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ReviewUseCaseRequestConverter {

	public static ReviewUseCaseRequest from(ReviewRequest request) {
		return ReviewUseCaseRequest.builder().build();
	}
}
