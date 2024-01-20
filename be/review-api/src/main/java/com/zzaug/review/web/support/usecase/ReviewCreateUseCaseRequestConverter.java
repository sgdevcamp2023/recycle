package com.zzaug.review.web.support.usecase;

import com.zzaug.review.domain.dto.review.ReviewCreateUseCaseRequest;
import com.zzaug.review.web.dto.review.ReviewRequest;
import com.zzaug.security.authentication.token.TokenUserDetails;
import lombok.experimental.UtilityClass;

import java.sql.Timestamp;

@UtilityClass
public class ReviewCreateUseCaseRequestConverter {

	public static ReviewCreateUseCaseRequest from(ReviewRequest request, Long questionId, TokenUserDetails userDetails) {
		return ReviewCreateUseCaseRequest.builder()
				.questionId(questionId)
				.content(request.getContent())
				.author(userDetails.getUsername())
				.authorId(Long.valueOf(userDetails.getId()))
				.createdAt(new Timestamp(System.currentTimeMillis()).toLocalDateTime())
				.startPoint(request.getStartPoint())
				.endPoint(request.getEndPoint())
				.tag(request.getTag())
				.build();
	}

}
