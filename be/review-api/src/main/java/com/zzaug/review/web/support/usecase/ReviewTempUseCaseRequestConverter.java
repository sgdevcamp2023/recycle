package com.zzaug.review.web.support.usecase;

import com.zzaug.review.domain.dto.review.ReviewTempCreateUseCaseRequest;
import com.zzaug.review.web.dto.review.ReviewTempRequest;
import com.zzaug.security.authentication.token.TokenUserDetails;
import java.sql.Timestamp;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ReviewTempUseCaseRequestConverter {
	public static ReviewTempCreateUseCaseRequest from(
			ReviewTempRequest request, Long questionId, TokenUserDetails userDetails) {
		return ReviewTempCreateUseCaseRequest.builder()
				.tempId(request.getTempId())
				.questionId(questionId)
				.content(request.getContent())
				.author(userDetails.getUsername())
				.authorId(Long.valueOf(userDetails.getId()))
				.startPoint(request.getStartPoint())
				.endPoint(request.getEndPoint())
				.createdAt(new Timestamp(System.currentTimeMillis()).toLocalDateTime())
				.build();
	}

	public static ReviewTempCreateUseCaseRequest from(
			ReviewTempRequest request, Long questionId, String author, Long authorId) {
		return ReviewTempCreateUseCaseRequest.builder()
				.tempId(request.getTempId())
				.questionId(questionId)
				.content(request.getContent())
				.author(author)
				.authorId(authorId)
				.startPoint(request.getStartPoint())
				.endPoint(request.getEndPoint())
				.createdAt(new Timestamp(System.currentTimeMillis()).toLocalDateTime())
				.build();
	}
}
