package com.zzaug.review.domain.usecase.review.event.converter;

import com.zzaug.review.domain.event.review.DeleteReviewEvent;
import com.zzaug.review.entity.review.ReviewEntity;
import org.springframework.stereotype.Component;

@Component
public class DeleteReviewEventConverter {
    public static DeleteReviewEvent from(ReviewEntity source) {
        return DeleteReviewEvent.builder()
                .reviewId(source.getReviewId())
                .questionId(source.getQuestionId())
                .content(source.getContent())
                .author(source.getAuthor())
                .authorId(source.getAuthorId())
                .createdAt(source.getCreatedAt())
                .updatedAt(source.getUpdatedAt())
                .startPoint(source.getStartPoint())
                .endPoint(source.getEndPoint())
                .tag(source.getTag())
                .isDeleted(true)
                .build();
    }
}
