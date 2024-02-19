package com.zzaug.review.domain.usecase.review.converter;

import com.zzaug.review.domain.dto.review.ReviewResponse;
import com.zzaug.review.entity.review.ReviewEntity;
import org.springframework.stereotype.Component;

@Component
public class ReviewResponseConverter {

	public static ReviewResponse from(ReviewEntity source) {
		return ReviewResponse.builder()
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
}
