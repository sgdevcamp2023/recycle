package com.zzaug.review.domain.support.entity;

import com.zzaug.review.domain.model.review.query.ReviewQuery;
import com.zzaug.review.entity.review.query.ReviewQueryEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ReviewQueryEntityConverter {

	public static ReviewQueryEntity from(ReviewQuery source) {
		return ReviewQueryEntity.builder()
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
				.isDeleted(source.getIsDeleted())
				.build();
	}
}
