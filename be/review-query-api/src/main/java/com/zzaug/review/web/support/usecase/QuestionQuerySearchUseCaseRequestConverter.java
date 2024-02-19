package com.zzaug.review.web.support.usecase;

import com.zzaug.review.domain.dto.question.query.QuestionQuerySearchUseCaseRequest;
import com.zzaug.review.web.dto.question.query.QuestionQuerySearchRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class QuestionQuerySearchUseCaseRequestConverter {

	public static QuestionQuerySearchUseCaseRequest from(QuestionQuerySearchRequest request) {
		return QuestionQuerySearchUseCaseRequest.builder()
				.authorId(request.getAuthorId())
				.query(request.getQuery())
				.pageRequest(request.getPageRequest())
				.build();
	}
}
