package com.zzaug.review.web.support.usecase;

import com.zzaug.review.domain.dto.review.query.ViewMemberReviewUseCaseRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ViewMemberReviewUseCaseRequestConverter {
    public static ViewMemberReviewUseCaseRequest from(Long authorId){
        return ViewMemberReviewUseCaseRequest.builder()
                .authorId(authorId)
                .build();
    }
}
