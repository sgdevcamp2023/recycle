package com.zzaug.review.domain.usecase.review.query;

import com.zzaug.review.domain.dto.review.query.ReviewQueryCreateUseCaseRequest;
import com.zzaug.review.domain.event.review.DeleteReviewEvent;
import com.zzaug.review.domain.event.review.EditReviewEvent;
import com.zzaug.review.domain.event.review.SaveReviewEvent;
import com.zzaug.review.domain.model.review.query.ReviewQuery;
import com.zzaug.review.entity.review.query.ReviewQueryEntity;
import org.springframework.stereotype.Component;

@Component
public class ReviewQueryConverter {

	public ReviewQuery from(ReviewQueryCreateUseCaseRequest source) {
		return ReviewQuery.builder()
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

	public ReviewQuery from(SaveReviewEvent source) {
		return ReviewQuery.builder()
				.reviewId(source.getReviewId())
				.questionId(source.getQuestionId())
				.content(source.getContent())
				.author(source.getAuthor())
				.authorId(source.getAuthorId())
				.createdAt(source.getCreatedAt())
				.startPoint(source.getStartPoint())
				.endPoint(source.getEndPoint())
				.tag(source.getTag())
				.isDeleted(false)
				.build();
	}

	public ReviewQuery from(EditReviewEvent source, ReviewQueryEntity reviewQueryEntity) {
		return ReviewQuery.builder()
				.reviewId(source.getReviewId())
				.questionId(source.getQuestionId())
				.content(source.getContent())
				.author(source.getAuthor())
				.authorId(source.getAuthorId())
				.createdAt(reviewQueryEntity.getCreatedAt())
				.updatedAt(source.getUpdatedAt())
				.startPoint(source.getStartPoint())
				.endPoint(source.getEndPoint())
				.tag(source.getTag())
				.isDeleted(reviewQueryEntity.isDeleted())
				.build();
	}

	public ReviewQuery from(DeleteReviewEvent source, ReviewQueryEntity reviewQueryEntity) {
		return ReviewQuery.builder()
				.reviewId(source.getReviewId())
				.questionId(source.getQuestionId())
				.content(reviewQueryEntity.getContent())
				.author(reviewQueryEntity.getAuthor())
				.authorId(reviewQueryEntity.getAuthorId())
				.createdAt(reviewQueryEntity.getCreatedAt())
				.updatedAt(reviewQueryEntity.getUpdatedAt())
				.startPoint(reviewQueryEntity.getStartPoint())
				.endPoint(reviewQueryEntity.getEndPoint())
				.tag(reviewQueryEntity.getTag())
				.isDeleted(true)
				.build();
	}
}
