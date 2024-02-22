package com.zzaug.review.domain.support.entity;

import com.zzaug.review.domain.model.review.Review;
import com.zzaug.review.entity.review.ReviewEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ReviewEntityConverter {

	public static ReviewEntity from(Review source) {
		return ReviewEntity.builder()
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
				.isDeleted(source.isDeleted())
				.build();
	}
}
