package com.zzaug.review.domain.support.entity;

import com.zzaug.review.domain.dto.comment.CommentResponse;
import com.zzaug.review.domain.model.comment.Comment;
import com.zzaug.review.entity.comment.CommentEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CommentEntityConverter {
	public static CommentEntity from(Comment source) {
		return CommentEntity.builder()
				.commentId(source.getCommentId())
				.questionId(source.getQuestionId())
				.content(source.getContent())
				.author(source.getAuthor())
				.authorId(source.getAuthorId())
				.createdAt(source.getCreatedAt())
				.updatedAt(source.getUpdatedAt())
				.parentId(source.getParentId())
				.build();
	}

	public static CommentResponse from(CommentEntity source) {
		return CommentResponse.builder()
				.commentId(source.getCommentId())
				.questionId(source.getQuestionId())
				.content(source.getContent())
				.author(source.getAuthor())
				.authorId(source.getAuthorId())
				.createdAt(source.getCreatedAt())
				.updatedAt(source.getUpdatedAt())
				.parentId(source.getParentId())
				.build();
	}
}
