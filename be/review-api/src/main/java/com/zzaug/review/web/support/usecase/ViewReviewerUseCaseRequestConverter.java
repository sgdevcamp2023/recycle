package com.zzaug.review.web.support.usecase;

import com.zzaug.review.domain.dto.member.ViewReviewerUseCaseRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ViewReviewerUseCaseRequestConverter {
	public static ViewReviewerUseCaseRequest from(Long questionId) {
		return ViewReviewerUseCaseRequest.builder().questionId(questionId).build();
	}
}
