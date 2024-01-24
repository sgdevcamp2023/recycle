package com.zzaug.review.domain.dto.question.query;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Setter
public class QuestionQueryDeleteUseCaseRequest {
    private Long questionId;
    private Long authorId;
}
