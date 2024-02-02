package com.zzaug.review.web.support.usecase;

import com.zzaug.review.domain.dto.review.query.SearchByReviewUseCaseRequest;
import com.zzaug.review.web.dto.review.query.ReviewSearchRequest;
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
