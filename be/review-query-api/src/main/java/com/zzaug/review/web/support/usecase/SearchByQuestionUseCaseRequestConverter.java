package com.zzaug.review.web.support.usecase;

import com.zzaug.review.domain.dto.review.query.SearchByQuestionUseCaseRequest;
import com.zzaug.review.web.dto.review.query.ReviewSearchByQuestionRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SearchByQuestionUseCaseRequestConverter {
	public static SearchByQuestionUseCaseRequest from(ReviewSearchByQuestionRequest request) {
		return SearchByQuestionUseCaseRequest.builder()
				.authorId(request.getAuthorId())
				.query(request.getQuery())
				.build();
	}
}
