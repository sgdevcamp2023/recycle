package com.zzaug.review.web.support.usecase;

import com.zzaug.review.domain.dto.question.QuestionTempCreateUseCaseRequest;
import com.zzaug.review.web.dto.question.QuestionTempRequest;
import com.zzaug.security.authentication.token.TokenUserDetails;
import java.sql.Timestamp;
import lombok.experimental.UtilityClass;

@UtilityClass
public class QuestionTempUseCaseRequestConverter {
	public static QuestionTempCreateUseCaseRequest from(
			QuestionTempRequest request, String author, Long authorId) {
		return QuestionTempCreateUseCaseRequest.builder()
				.tempId(request.getTempId())
				.content(request.getContent())
				.author(author)
				.authorId(authorId)
				.createdAt(new Timestamp(System.currentTimeMillis()).toLocalDateTime())
				.build();
	}
}
