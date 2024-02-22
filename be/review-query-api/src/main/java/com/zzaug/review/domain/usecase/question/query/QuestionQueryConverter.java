package com.zzaug.review.domain.usecase.question.query;

import com.zzaug.review.domain.dto.question.query.QuestionQueryCreateUseCaseRequest;
import com.zzaug.review.domain.dto.question.query.QuestionQueryEditUseCaseRequest;
import com.zzaug.review.domain.event.question.DeleteQuestionEvent;
import com.zzaug.review.domain.event.question.SaveQuestionEvent;
import com.zzaug.review.domain.model.question.query.QuestionQuery;
import com.zzaug.review.entity.question.query.QuestionQueryEntity;
import org.springframework.stereotype.Component;

@Component
public class QuestionQueryConverter {

	public QuestionQuery from(QuestionQueryCreateUseCaseRequest source) {
		return QuestionQuery.builder()
				.questionId(source.getQuestionId())
				.content(source.getContent())
				.author(source.getAuthor())
				.authorId(source.getAuthorId())
				.reviewCnt(source.getReviewCnt())
				.createdAt(source.getCreatedAt())
				.build();
	}

	public QuestionQuery from(QuestionQueryEntity source) {
		return QuestionQuery.builder()
				.questionId(source.getQuestionId())
				.content(source.getContent())
				.author(source.getAuthor())
				.authorId(source.getAuthorId())
				.reviewCnt(source.getReviewCnt())
				.createdAt(source.getCreatedAt())
				.build();
	}

	public QuestionQuery from(SaveQuestionEvent source) {
		return QuestionQuery.builder()
				.questionId(source.getQuestionId())
				.content(source.getContent())
				.author(source.getAuthor())
				.authorId(source.getAuthorId())
				.reviewCnt(source.getReviewCnt())
				.createdAt(source.getCreatedAt())
				.build();
	}

	public QuestionQuery from(DeleteQuestionEvent source, QuestionQueryEntity questionQueryEntity) {
		return QuestionQuery.builder()
				.questionId(source.getQuestionId())
				.content(questionQueryEntity.getContent())
				.author(source.getAuthor())
				.authorId(source.getAuthorId())
				.reviewCnt(questionQueryEntity.getReviewCnt())
				.createdAt(questionQueryEntity.getCreatedAt())
				.deletedAt(source.getDeletedAt())
				.isDeleted(true)
				.build();
	}

	public QuestionQuery from(
			QuestionQueryEditUseCaseRequest source, QuestionQueryEntity questionQueryEntity) {
		return QuestionQuery.builder()
				.questionId(questionQueryEntity.getQuestionId())
				.content(source.getContent())
				.author(questionQueryEntity.getAuthor())
				.authorId(questionQueryEntity.getAuthorId())
				.reviewCnt(questionQueryEntity.getReviewCnt())
				.createdAt(questionQueryEntity.getCreatedAt())
				.build();
	}
}
