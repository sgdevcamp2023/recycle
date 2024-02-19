package com.zzaug.review.web.support.usecase.comment;

import com.zzaug.review.domain.dto.comment.CommentEditUseCaseRequest;
import com.zzaug.review.web.dto.review.CommentRequest;
import com.zzaug.security.authentication.token.TokenUserDetails;
import java.sql.Timestamp;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CommentEditUseCaseRequestConverter {
	public static CommentEditUseCaseRequest from(
			CommentRequest request, Long commentId, Long questionId, TokenUserDetails userDetails) {
		return CommentEditUseCaseRequest.builder()
				.commentId(commentId)
				.questionId(questionId)
				.content(request.getContent())
				.author(userDetails.getUsername())
				.authorId(Long.valueOf(userDetails.getId()))
				.parentId(request.getParentId())
				.updatedAt(new Timestamp(System.currentTimeMillis()).toLocalDateTime())
				.build();
	}
}
