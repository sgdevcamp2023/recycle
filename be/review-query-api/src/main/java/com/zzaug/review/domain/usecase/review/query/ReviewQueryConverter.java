package com.zzaug.review.domain.usecase.review.query;

import com.zzaug.review.domain.dto.review.query.ReviewQueryCreateUseCaseRequest;
import com.zzaug.review.domain.dto.review.query.ReviewQueryEditUseCaseRequest;
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

	public ReviewQuery from(
			ReviewQueryEditUseCaseRequest source, ReviewQueryEntity reviewQueryEntity) {
		return ReviewQuery.builder()
				.reviewId(reviewQueryEntity.getReviewId())
				.questionId(reviewQueryEntity.getQuestionId())
				.content(source.getContent())
				.author(reviewQueryEntity.getAuthor())
				.authorId(reviewQueryEntity.getAuthorId())
				.createdAt(reviewQueryEntity.getCreatedAt())
				.updatedAt(source.getUpdatedAt())
				.startPoint(source.getStartPoint())
				.endPoint(source.getEndPoint())
				.tag(reviewQueryEntity.getTag())
				.build();
	}
}
