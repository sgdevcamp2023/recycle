package com.zzaug.review.domain.support.entity;

import com.zzaug.review.domain.event.question.QuestionRequestEvent;
import com.zzaug.review.entity.question.QuestionRequestEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class QuestionRequestEntityConverter {
    public QuestionRequestEntity from(QuestionRequestEvent source) {
        return QuestionRequestEntity.builder()
                .questionReqId(source.getQuestionReqId())
                .requester(source.getRequester())
                .requesterId(source.getRequesterId())
                .build();
    }
}
