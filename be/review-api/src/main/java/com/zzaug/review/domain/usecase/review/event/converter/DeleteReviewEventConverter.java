package com.zzaug.review.domain.usecase.review.event.converter;

import com.zzaug.review.domain.event.review.DeleteReviewEvent;
import com.zzaug.review.entity.review.ReviewEntity;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class DeleteReviewEventConverter {
	public static DeleteReviewEvent from(ReviewEntity source) {
		return DeleteReviewEvent.builder()
				.reviewId(source.getReviewId())
				.questionId(source.getQuestionId())
				.author(source.getAuthor())
				.authorId(source.getAuthorId())
				.deletedAt(source.getDeletedAt())
				.eventAt(new Timestamp(System.currentTimeMillis()).toLocalDateTime())
				.build();
	}
}
