package com.zzaug.review.domain.usecase.question.converter;

import com.zzaug.review.domain.dto.question.QuestionResponse;
import com.zzaug.review.domain.model.question.Question;
import com.zzaug.review.entity.question.QuestionEntity;
import org.springframework.stereotype.Component;

@Component
public class QuestionResponseConverter {

	public QuestionResponse from(Question source) {
		return QuestionResponse.builder()
				.questionId(source.getQuestionId())
				.content(source.getContent())
				.author(source.getAuthor())
				.authorId(source.getAuthorId())
				.reviewCnt(source.getReviewCnt())
				.createdAt(source.getCreatedAt())
				.updatedAt(source.getUpdatedAt())
				.build();
	}

	public static QuestionResponse from(QuestionEntity source) {
		return QuestionResponse.builder()
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
