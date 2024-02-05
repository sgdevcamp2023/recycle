package com.zzaug.review.domain.usecase.question.converter;

import com.zzaug.review.domain.event.question.DeleteQuestionEvent;
import com.zzaug.review.entity.question.QuestionEntity;
import org.springframework.stereotype.Component;

@Component
public class DeleteQuestionEventConverter {
    public static DeleteQuestionEvent from(QuestionEntity source) {
        return DeleteQuestionEvent.builder()
                .questionId(source.getQuestionId())
                .content(source.getContent())
                .author(source.getAuthor())
                .authorId(source.getAuthorId())
                .createdAt(source.getCreatedAt())
                .updatedAt(source.getUpdatedAt())
                .reviewCnt(source.getReviewCnt())
                .isDeleted(true)
                .build();
    }
}
