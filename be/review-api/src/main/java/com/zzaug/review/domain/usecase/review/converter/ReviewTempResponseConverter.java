package com.zzaug.review.domain.usecase.review.converter;

import com.zzaug.review.domain.dto.review.ReviewTempResponse;
import com.zzaug.review.entity.review.ReviewTempEntity;
import org.springframework.stereotype.Component;

@Component
public class ReviewTempResponseConverter {
	public static ReviewTempResponse from(ReviewTempEntity source) {
		return ReviewTempResponse.builder()
				.tempId(source.getTempId())
				.questionId(source.getQuestionId())
				.content(source.getContent())
				.author(source.getAuthor())
				.authorId(source.getAuthorId())
				.createdAt(source.getCreatedAt())
				.startPoint(source.getStartPoint())
				.endPoint(source.getEndPoint())
				.tag(source.getTag())
				.build();
	}
}
