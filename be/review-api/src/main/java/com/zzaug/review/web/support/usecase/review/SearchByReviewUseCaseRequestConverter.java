package com.zzaug.review.web.support.usecase.review;

import com.zzaug.review.domain.dto.review.SearchByReviewUseCaseRequest;
import com.zzaug.review.web.dto.review.ReviewSearchRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SearchByReviewUseCaseRequestConverter {
	public static SearchByReviewUseCaseRequest from(ReviewSearchRequest request) {
		return SearchByReviewUseCaseRequest.builder()
				.authorId(request.getAuthorId())
				.query(request.getQuery())
				.build();
	}
}
