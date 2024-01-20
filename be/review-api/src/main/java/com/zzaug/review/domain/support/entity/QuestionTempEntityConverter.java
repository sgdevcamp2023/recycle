package com.zzaug.review.domain.support.entity;

import com.zzaug.review.domain.model.question.QuestionTemp;
import com.zzaug.review.entity.question.QuestionTempEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class QuestionTempEntityConverter {
    public static QuestionTempEntity from (QuestionTemp source){
        return QuestionTempEntity.builder()
                .tId(source.getTId())
                .content(source.getContent())
                .author(source.getAuthor())
                .authorId(source.getAuthorId())
                .createdAt(source.getCreatedAt())
                .build();
    }
}
