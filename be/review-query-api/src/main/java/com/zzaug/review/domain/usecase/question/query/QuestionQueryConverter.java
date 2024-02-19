package com.zzaug.review.domain.usecase.question.query;

import com.zzaug.review.domain.model.question.query.QuestionQuery;
import com.zzaug.review.entity.question.query.QuestionQueryEntity;
import org.springframework.stereotype.Component;

@Component
public class QuestionQueryConverter {

	public QuestionQuery from(QuestionQueryEntity source) {
		return QuestionQuery.builder()
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
