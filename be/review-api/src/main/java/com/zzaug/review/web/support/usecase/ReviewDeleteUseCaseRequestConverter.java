package com.zzaug.review.web.support.usecase;

import com.zzaug.review.domain.dto.review.ReviewDeleteUseCaseRequest;
import com.zzaug.review.web.dto.review.ReviewDeleteRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ReviewDeleteUseCaseRequestConverter {

    public static ReviewDeleteUseCaseRequest from (ReviewDeleteRequest request){
        return ReviewDeleteUseCaseRequest.builder()
                .reviewId(request.getReviewId())
                .questionId(request.getQuestionId())
                .authorId(request.getAuthorId())
                .build();
    }
}
