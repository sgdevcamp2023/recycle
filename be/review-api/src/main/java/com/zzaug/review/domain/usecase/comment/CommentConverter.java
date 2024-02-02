package com.zzaug.review.domain.usecase.comment;

import com.zzaug.review.domain.dto.comment.CommentCreateUseCaseRequest;
import com.zzaug.review.domain.model.comment.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentConverter {
	public Comment from(CommentCreateUseCaseRequest source) {
		return Comment.builder()
				.commentId(source.getCommentId())
				.questionId(source.getQuestionId())
				.content(source.getContent())
				.author(source.getAuthor())
				.authorId(source.getAuthorId())
				.createdAt(source.getCreatedAt())
				.parentId(source.getParentId())
				.updatedAt(source.getUpdatedAt())
				.build();
	}
}
