package com.zzaug.review.domain.support.entity;

import com.zzaug.review.domain.model.question.QuestionTemp;
import com.zzaug.review.entity.question.QuestionTempEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class QuestionTempEntityConverter {
    public static QuestionTempEntity from (QuestionTemp source){
        return QuestionTempEntity.builder()
                .t_id(source.getT_id())
                .content(source.getContent())
                .author(source.getAuthor())
                .author_id(source.getAuthor_id())
                .created_at(source.getCreated_at())
                .build();
    }
}
