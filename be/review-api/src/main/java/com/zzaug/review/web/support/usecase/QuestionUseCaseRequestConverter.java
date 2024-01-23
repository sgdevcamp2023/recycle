package com.zzaug.review.web.support.usecase;

import com.zzaug.review.domain.dto.question.QuestionUseCaseRequest;
import com.zzaug.review.web.dto.question.QuestionRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class QuestionUseCaseRequestConverter {
	public static QuestionUseCaseRequest from(QuestionRequest request) {
		return QuestionUseCaseRequest.builder().content(request.getContent()).build();
	}
}
