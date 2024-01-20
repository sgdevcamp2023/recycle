package com.zzaug.review.domain.support.entity;

import com.zzaug.review.domain.model.review.ReviewTemp;
import com.zzaug.review.entity.review.ReviewTempEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ReviewTempEntityConverter {

    public static ReviewTempEntity from (ReviewTemp source){
        return ReviewTempEntity.builder()
                .tId(source.getTId())
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
