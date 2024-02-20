package com.zzaug.review.web.support.usecase;

import com.zzaug.review.domain.dto.review.ReviewCreateUseCaseRequest;
import com.zzaug.review.web.dto.review.ReviewRequest;
import com.zzaug.security.authentication.token.TokenUserDetails;
import java.sql.Timestamp;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ReviewCreateUseCaseRequestConverter {

	public static ReviewCreateUseCaseRequest from(
			ReviewRequest request, Long questionId, TokenUserDetails userDetails) {
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

	public static ReviewCreateUseCaseRequest from(
			ReviewRequest request, Long questionId, String author, Long authorId) {
		return ReviewCreateUseCaseRequest.builder()
				.questionId(questionId)
				.content(request.getContent())
				.author(author)
				.authorId(authorId)
				.createdAt(new Timestamp(System.currentTimeMillis()).toLocalDateTime())
				.startPoint(request.getStartPoint())
				.endPoint(request.getEndPoint())
				.tag(request.getTag())
				.build();
	}
}
