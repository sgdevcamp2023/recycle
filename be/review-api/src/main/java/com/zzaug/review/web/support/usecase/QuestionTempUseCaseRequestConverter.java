package com.zzaug.review.web.support.usecase;

import com.zzaug.review.domain.dto.question.QuestionTempUseCaseRequest;
import com.zzaug.review.web.dto.question.QuestionTempRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class QuestionTempUseCaseRequestConverter {
	public static QuestionTempUseCaseRequest from(QuestionTempRequest request) {
		return QuestionTempUseCaseRequest.builder()
				.t_id(request.getT_id())
				.content(request.getContent())
				.build();
	}
}
