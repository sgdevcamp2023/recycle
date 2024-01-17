package com.zzaug.review.web.support.usecase;

import com.zzaug.review.domain.dto.question.QuestionTempCreateUseCaseRequest;
import com.zzaug.review.web.dto.question.QuestionTempRequest;
import com.zzaug.security.authentication.token.TokenUserDetails;
import java.sql.Timestamp;
import lombok.experimental.UtilityClass;

@UtilityClass
public class QuestionTempUseCaseRequestConverter {
	public static QuestionTempCreateUseCaseRequest from(
			QuestionTempRequest request, TokenUserDetails userDetails) {
		return QuestionTempCreateUseCaseRequest.builder()
				.t_id(request.getT_id())
				.content(request.getContent())
				.author(userDetails.getUsername())
				.author_id(Long.valueOf(userDetails.getId()))
				.created_at(new Timestamp(System.currentTimeMillis()).toLocalDateTime())
				.build();
	}

}
