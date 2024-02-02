package com.zzaug.review.domain.usecase.review.converter;

import com.zzaug.review.domain.dto.review.ReviewCreateUseCaseRequest;
import com.zzaug.review.domain.dto.review.ReviewEditUseCaseRequest;
import com.zzaug.review.domain.model.review.Review;
import com.zzaug.review.entity.review.ReviewEntity;
import org.springframework.stereotype.Component;

@Component
public class ReviewConverter {
	public Review from(ReviewCreateUseCaseRequest source) {
		return Review.builder()
				.reviewId(source.getReviewId())
				.questionId(source.getQuestionId())
				.content(source.getContent())
				.author(source.getAuthor())
				.authorId(source.getAuthorId())
				.createdAt(source.getCreatedAt())
				.updatedAt(source.getUpdatedAt())
				.startPoint(source.getStartPoint())
				.endPoint(source.getEndPoint())
				.tag(source.getTag())
				.build();
	}

	public Review from(ReviewEditUseCaseRequest source) {
		return Review.builder()
				.reviewId(source.getReviewId())
				.questionId(source.getQuestionId())
				.content(source.getContent())
				.author(source.getAuthor())
				.authorId(source.getAuthorId())
				.updatedAt(source.getUpdatedAt())
				.startPoint(source.getStartPoint())
				.endPoint(source.getEndPoint())
				.build();
	}

	public Review from(ReviewEntity source) {
		return Review.builder()
				.reviewId(source.getReviewId())
				.questionId(source.getQuestionId())
				.content(source.getContent())
				.author(source.getAuthor())
				.authorId(source.getAuthorId())
				.updatedAt(source.getUpdatedAt())
				.startPoint(source.getStartPoint())
				.endPoint(source.getEndPoint())
				.build();
	}
}
