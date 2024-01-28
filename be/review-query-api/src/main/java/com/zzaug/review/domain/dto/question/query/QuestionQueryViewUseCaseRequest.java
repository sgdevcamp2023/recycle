package com.zzaug.review.domain.dto.question.query;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class QuestionQueryViewUseCaseRequest {
    private Long questionId;
}
