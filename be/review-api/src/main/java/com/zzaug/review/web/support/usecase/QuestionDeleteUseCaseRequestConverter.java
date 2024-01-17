package com.zzaug.review.web.support.usecase;

import com.zzaug.review.domain.dto.question.QuestionDeleteUseCaseRequest;
import com.zzaug.review.web.dto.question.QuestionDeleteRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class QuestionDeleteUseCaseRequestConverter {
    public static QuestionDeleteUseCaseRequest from(
            QuestionDeleteRequest request) {
        return QuestionDeleteUseCaseRequest.builder()
                .question_id(request.getQuestion_id())
                .author_id(request.getAuthor_id())
                .build();
    }
}
