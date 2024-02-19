package com.zzaug.review.web.support.usecase.question;

import com.zzaug.review.domain.dto.question.QuestionDeleteUseCaseRequest;
import com.zzaug.review.web.dto.question.QuestionDeleteRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class QuestionDeleteUseCaseRequestConverter {
	public static QuestionDeleteUseCaseRequest from(QuestionDeleteRequest request) {
		return QuestionDeleteUseCaseRequest.builder()
				.questionId(request.getQuestionId())
				.authorId(request.getAuthorId())
				.build();
	}
}
