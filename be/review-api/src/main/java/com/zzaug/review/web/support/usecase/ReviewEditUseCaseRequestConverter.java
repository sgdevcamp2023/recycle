package com.zzaug.review.web.support.usecase;

import com.zzaug.review.domain.dto.review.ReviewEditUseCaseRequest;
import com.zzaug.review.web.dto.review.ReviewRequest;
import com.zzaug.security.authentication.token.TokenUserDetails;
import lombok.experimental.UtilityClass;

import java.sql.Timestamp;

@UtilityClass
public class ReviewEditUseCaseRequestConverter {

    public static ReviewEditUseCaseRequest from (ReviewRequest request, Long reviewId, Long questionId, TokenUserDetails userDetails){
        return ReviewEditUseCaseRequest.builder()
                .reviewId(reviewId)
                .questionId(questionId)
                .content(request.getContent())
                .author(userDetails.getUsername())
                .authorId(Long.valueOf(userDetails.getId()))
                .updatedAt(new Timestamp(System.currentTimeMillis()).toLocalDateTime())
                .startPoint(request.getStartPoint())
                .endPoint(request.getEndPoint())
                .build();
    }

}
