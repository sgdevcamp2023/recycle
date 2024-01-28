package com.zzaug.review.domain.usecase.question.query;

import com.zzaug.review.domain.dto.question.query.QuestionQueryResponse;
import com.zzaug.review.domain.model.question.query.QuestionQuery;
import org.springframework.stereotype.Component;

@Component
public class QuestionQueryResponseConverter {

    public QuestionQueryResponse from(QuestionQuery source){
        return QuestionQueryResponse.builder()
                .question_id(source.getQuestionId())
                .content(source.getContent())
                .author(source.getAuthor())
                .author_id(source.getAuthorId())
                .review_cnt(source.getReviewCnt())
                .created_at(source.getCreatedAt())
                .updated_at(source.getUpdatedAt())
                .build();
    }
}
