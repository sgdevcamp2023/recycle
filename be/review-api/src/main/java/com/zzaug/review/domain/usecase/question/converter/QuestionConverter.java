package com.zzaug.review.domain.usecase.question.converter;

import com.zzaug.review.domain.dto.question.QuestionCreateUseCaseRequest;
import com.zzaug.review.domain.model.question.Question;
import org.springframework.stereotype.Component;

@Component
public class QuestionConverter {

	public Question from(QuestionCreateUseCaseRequest source) {
		return Question.builder()
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
