package com.zzaug.review.domain.usecase.question.converter;

import com.zzaug.review.domain.dto.question.QuestionCreateUseCaseRequest;
import com.zzaug.review.domain.model.question.Question;
import org.springframework.stereotype.Component;

@Component
public class QuestionConverter {

	public Question from(QuestionCreateUseCaseRequest source) {
		return Question.builder()
				.questionId(source.getQuestionId())
				.content(source.getContent())
				.author(source.getAuthor())
				.authorId(source.getAuthorId())
				.reviewCnt(source.getReviewCnt())
				.createdAt(source.getCreatedAt())
				.isDeleted(source.isDeleted())
				.build();
	}
}
