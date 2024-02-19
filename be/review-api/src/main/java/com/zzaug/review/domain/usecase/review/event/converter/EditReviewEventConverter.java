package com.zzaug.review.domain.usecase.review.event.converter;

import com.zzaug.review.domain.event.review.EditReviewEvent;
import com.zzaug.review.entity.review.ReviewEntity;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class EditReviewEventConverter {
	public static EditReviewEvent from(ReviewEntity source) {
		return EditReviewEvent.builder()
				.reviewId(source.getReviewId())
				.questionId(source.getQuestionId())
				.content(source.getContent())
				.author(source.getAuthor())
				.authorId(source.getAuthorId())
				.updatedAt(source.getUpdatedAt())
				.startPoint(source.getStartPoint())
				.endPoint(source.getEndPoint())
				.tag(source.getTag())
				.eventAt(new Timestamp(System.currentTimeMillis()).toLocalDateTime())
				.build();
	}
}
