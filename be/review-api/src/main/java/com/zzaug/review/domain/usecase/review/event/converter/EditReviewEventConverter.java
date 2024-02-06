package com.zzaug.review.domain.usecase.review.event.converter;

import com.zzaug.review.domain.event.review.EditReviewEvent;
import com.zzaug.review.entity.review.ReviewEntity;
import org.springframework.stereotype.Component;

@Component
public class EditReviewEventConverter {
    public static EditReviewEvent from(ReviewEntity source) {
        return EditReviewEvent.builder()
                .reviewId(source.getReviewId())
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
