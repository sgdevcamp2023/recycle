package com.zzaug.review.web.support.usecase;

import com.zzaug.review.domain.dto.review.query.ViewMemberQuestionUseCaseRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ViewMemberQuestionUseCaseRequestConverter {
    public static ViewMemberQuestionUseCaseRequest from(Long authorId){
        return ViewMemberQuestionUseCaseRequest.builder()
                .authorId(authorId)
                .build();
    }
}
