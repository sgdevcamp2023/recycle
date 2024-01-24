package com.zzaug.review.domain.support.entity;

import com.zzaug.review.domain.model.question.query.QuestionQuery;
import com.zzaug.review.entity.question.query.QuestionQueryEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class QuestionQueryEntityConverter {
	public static QuestionQueryEntity from(QuestionQuery source) {
		return QuestionQueryEntity.builder()
				.questionId(source.getQuestionId())
				.content(source.getContent())
				.author(source.getAuthor())
				.authorId(source.getAuthorId())
				.reviewCnt(source.getReviewCnt())
				.createdAt(source.getCreatedAt())
				.updatedAt(source.getUpdatedAt())
				.build();
	}
}
