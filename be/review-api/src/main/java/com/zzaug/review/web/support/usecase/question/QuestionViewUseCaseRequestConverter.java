package com.zzaug.review.web.support.usecase.question;

import com.zzaug.review.domain.dto.question.QuestionViewUseCaseRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class QuestionViewUseCaseRequestConverter {
	public static QuestionViewUseCaseRequest from(Long questionId) {
		return QuestionViewUseCaseRequest.builder().questionId(questionId).build();
	}
}
