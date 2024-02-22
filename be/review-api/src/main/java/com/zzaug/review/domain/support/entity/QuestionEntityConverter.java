package com.zzaug.review.domain.support.entity;

import com.zzaug.review.domain.model.question.Question;
import com.zzaug.review.entity.question.QuestionEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class QuestionEntityConverter {
	public static QuestionEntity from(Question source) {
		return QuestionEntity.builder()
				.questionId(source.getQuestionId())
				.content(source.getContent())
				.author(source.getAuthor())
				.authorId(source.getAuthorId())
				.reviewCnt(source.getReviewCnt())
				.createdAt(source.getCreatedAt())
				.deletedAt(source.getDeletedAt())
				.build();
	}
}
