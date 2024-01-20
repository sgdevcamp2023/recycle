package com.zzaug.review.web.support.usecase;

import com.zzaug.review.domain.dto.question.QuestionCreateUseCaseRequest;
import com.zzaug.review.web.dto.question.QuestionRequest;
import com.zzaug.security.authentication.token.TokenUserDetails;
import java.sql.Timestamp;
import lombok.experimental.UtilityClass;

@UtilityClass
public class QuestionCreateUseCaseRequestConverter {
	public static QuestionCreateUseCaseRequest from(
			QuestionRequest request, TokenUserDetails userDetails) {
		return QuestionCreateUseCaseRequest.builder()
				.content(request.getContent())
				.author(userDetails.getUsername())
				.authorId(Long.valueOf(userDetails.getId()))
				.reviewCnt(0)
				.createdAt(new Timestamp(System.currentTimeMillis()))
				.build();
	}
}
