package com.zzaug.review.domain.support.entity;

import com.zzaug.review.domain.model.question.Question;
import com.zzaug.review.entity.question.QuestionEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class QuestionEntityConverter {
	public static QuestionEntity from(Question source) {
		return QuestionEntity.builder()
				.question_id(source.getQuestion_id())
				.content(source.getContent())
				.author(source.getAuthor())
				.author_id(source.getAuthor_id())
				.review_cnt(source.getReview_cnt())
				.created_at(source.getCreated_at())
				.updated_at(source.getUpdated_at())
				.build();
	}
}
