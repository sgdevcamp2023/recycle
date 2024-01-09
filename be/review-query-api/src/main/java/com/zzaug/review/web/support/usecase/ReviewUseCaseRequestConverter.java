package com.zzaug.review.web.support.usecase;

import com.zzaug.review.domain.dto.review.query.ReviewQueryUseCaseRequest;
import com.zzaug.review.web.dto.review.query.ReviewQueryRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ReviewUseCaseRequestConverter {

	public static ReviewQueryUseCaseRequest from(ReviewQueryRequest request) {
		return ReviewQueryUseCaseRequest.builder().name(request.getName()).build();
	}
}
