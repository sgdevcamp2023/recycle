package com.zzaug.review.web.support.usecase;

import com.zzaug.review.domain.dto.comment.CommentCreateUseCaseRequest;
import com.zzaug.review.web.dto.review.CommentRequest;
import com.zzaug.security.authentication.token.TokenUserDetails;
import java.sql.Timestamp;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CommentCreateUseCaseRequestConverter {
	public static CommentCreateUseCaseRequest from(
			CommentRequest request, Long questionId, TokenUserDetails userDetails) {
		return CommentCreateUseCaseRequest.builder()
				.questionId(questionId)
				.content(request.getContent())
				.author(userDetails.getUsername())
				.authorId(Long.valueOf(userDetails.getId()))
				.parentId(request.getParentId())
				.createdAt(new Timestamp(System.currentTimeMillis()).toLocalDateTime())
				.build();
	}
}
