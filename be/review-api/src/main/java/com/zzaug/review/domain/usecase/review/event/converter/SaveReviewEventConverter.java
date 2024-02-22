package com.zzaug.review.domain.usecase.review.event.converter;

import com.zzaug.review.domain.event.review.SaveReviewEvent;
import com.zzaug.review.entity.review.ReviewEntity;
import java.sql.Timestamp;
import org.springframework.stereotype.Component;

@Component
public class SaveReviewEventConverter {
	public static SaveReviewEvent from(ReviewEntity source) {
		return SaveReviewEvent.builder()
				.reviewId(source.getReviewId())
				.questionId(source.getQuestionId())
				.content(source.getContent())
				.author(source.getAuthor())
				.authorId(source.getAuthorId())
				.createdAt(source.getCreatedAt())
				.startPoint(source.getStartPoint())
				.endPoint(source.getEndPoint())
				.tag(source.getTag())
				.eventAt(new Timestamp(System.currentTimeMillis()).toLocalDateTime())
				.build();
	}
}
