package com.zzaug.review.domain.usecase.question.query;

import com.zzaug.review.domain.dto.question.query.QuestionQueryResponse;
import com.zzaug.review.entity.question.query.QuestionQueryEntity;
import org.springframework.stereotype.Component;

@Component
public class QuestionQueryResponseConverter {

	public static QuestionQueryResponse from(QuestionQueryEntity source) {
		return QuestionQueryResponse.builder()
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
