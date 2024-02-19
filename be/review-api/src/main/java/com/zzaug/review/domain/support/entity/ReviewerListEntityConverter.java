package com.zzaug.review.domain.support.entity;

import com.zzaug.review.domain.model.member.ReviewerList;
import com.zzaug.review.entity.member.ReviewerListEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ReviewerListEntityConverter {
    public static ReviewerListEntity from (ReviewerList source) {
        return ReviewerListEntity.builder()
                .reviewerListId(source.getReviewerListId())
                .reviewerName(source.getReviewerName())
                .reviewerId(source.getReviewerId())
                .questionId(source.getQuestionId())
                .build();
    }
}
