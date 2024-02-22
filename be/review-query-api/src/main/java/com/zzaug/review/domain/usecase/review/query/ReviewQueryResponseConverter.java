package com.zzaug.review.domain.usecase.review.query;

import com.zzaug.review.domain.dto.review.query.ReviewQueryResponse;
import com.zzaug.review.entity.review.query.ReviewQueryEntity;
import org.springframework.stereotype.Component;

@Component
public class ReviewQueryResponseConverter {

	public static ReviewQueryResponse from(ReviewQueryEntity source) {
		return ReviewQueryResponse.builder()
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
