package com.zzaug.review.web.support.usecase.review;


import com.zzaug.review.domain.dto.review.SearchByQuestionUseCaseRequest;
import com.zzaug.review.web.dto.review.ReviewSearchByQuestionRequest;
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
