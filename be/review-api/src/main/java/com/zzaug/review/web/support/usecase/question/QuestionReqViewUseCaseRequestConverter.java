package com.zzaug.review.web.support.usecase.question;

import com.zzaug.review.domain.dto.question.QuestionReqViewUseCaseRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class QuestionReqViewUseCaseRequestConverter {
	public static QuestionReqViewUseCaseRequest from(Long receiverId) {
		return QuestionReqViewUseCaseRequest.builder().receiverId(receiverId).build();
	}
}
