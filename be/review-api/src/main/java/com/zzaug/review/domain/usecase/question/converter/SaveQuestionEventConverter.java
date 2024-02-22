package com.zzaug.review.domain.usecase.question.converter;

import com.zzaug.review.domain.event.question.SaveQuestionEvent;
import com.zzaug.review.entity.question.QuestionEntity;
import java.sql.Timestamp;
import org.springframework.stereotype.Component;

@Component
public class SaveQuestionEventConverter {
	public static SaveQuestionEvent from(QuestionEntity source) {
		return SaveQuestionEvent.builder()
				.questionId(source.getQuestionId())
				.content(source.getContent())
				.author(source.getAuthor())
				.authorId(source.getAuthorId())
				.createdAt(source.getCreatedAt())
				.deletedAt(source.getDeletedAt())
				.reviewCnt(source.getReviewCnt())
				.eventAt(new Timestamp(System.currentTimeMillis()).toLocalDateTime())
				.build();
	}
}
