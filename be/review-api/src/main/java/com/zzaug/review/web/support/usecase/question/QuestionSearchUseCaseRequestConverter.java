package com.zzaug.review.web.support.usecase.question;

import com.zzaug.review.domain.dto.question.QuestionSearchUseCaseRequest;
import com.zzaug.review.web.dto.question.QuestionSearchRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class QuestionSearchUseCaseRequestConverter {

	public static QuestionSearchUseCaseRequest from(QuestionSearchRequest request) {
		return QuestionSearchUseCaseRequest.builder()
				.authorId(request.getAuthorId())
				.query(request.getQuery())
				.pageRequest(request.getPageRequest())
				.build();
	}
}
