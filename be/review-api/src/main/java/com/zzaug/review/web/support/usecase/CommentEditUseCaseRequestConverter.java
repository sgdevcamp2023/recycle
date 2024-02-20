package com.zzaug.review.web.support.usecase;

import com.zzaug.review.domain.dto.comment.CommentEditUseCaseRequest;
import com.zzaug.review.web.dto.review.CommentRequest;
import com.zzaug.security.authentication.token.TokenUserDetails;
import java.sql.Timestamp;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CommentEditUseCaseRequestConverter {
	public static CommentEditUseCaseRequest from(
			CommentRequest request, Long commentId, Long questionId, String author, Long authorId) {
		return CommentEditUseCaseRequest.builder()
				.commentId(commentId)
				.questionId(questionId)
				.content(request.getContent())
				.author(author)
				.authorId(authorId)
				.parentId(request.getParentId())
				.updatedAt(new Timestamp(System.currentTimeMillis()).toLocalDateTime())
				.build();
	}
}
