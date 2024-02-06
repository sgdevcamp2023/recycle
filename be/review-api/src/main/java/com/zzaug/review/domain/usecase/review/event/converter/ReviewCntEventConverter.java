package com.zzaug.review.domain.usecase.review.event.converter;

import com.zzaug.review.domain.event.review.ReviewCntEvent;
import com.zzaug.review.entity.question.QuestionEntity;
import org.springframework.stereotype.Component;

@Component
public class ReviewCntEventConverter {
	public static ReviewCntEvent from(QuestionEntity source) {
		return ReviewCntEvent.builder()
				.questionId(source.getQuestionId())
				.content(source.getContent())
				.author(source.getAuthor())
				.authorId(source.getAuthorId())
				.createdAt(source.getCreatedAt())
				.updatedAt(source.getUpdatedAt())
				.reviewCnt(source.getReviewCnt())
				.build();
	}
}
