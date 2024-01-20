package com.zzaug.review.domain.usecase.question.converter;

import com.zzaug.review.domain.dto.question.QuestionTempCreateUseCaseRequest;
import com.zzaug.review.domain.model.question.QuestionTemp;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class QuestionTempConverter {

    public QuestionTemp from (QuestionTempCreateUseCaseRequest source){
        return QuestionTemp.builder()
                .tId(source.getTId())
                .content(source.getContent())
                .author(source.getAuthor())
                .authorId(Long.valueOf(source.getAuthorId()))
                .createdAt(new Timestamp(System.currentTimeMillis()).toLocalDateTime())
                .build();
    }
}
