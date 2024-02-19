package com.zzaug.review.domain.usecase.question.converter;

import com.zzaug.review.domain.dto.question.QuestionTempResponse;
import com.zzaug.review.entity.question.QuestionTempEntity;
import org.springframework.stereotype.Component;

@Component
public class QuestionTempResponseConverter {
	public static QuestionTempResponse from(QuestionTempEntity source) {
		return QuestionTempResponse.builder()
				.tempId(source.getTempId())
				.content(source.getContent())
				.author(source.getAuthor())
				.authorId(source.getAuthorId())
				.createdAt(source.getCreatedAt())
				.build();
	}
}
