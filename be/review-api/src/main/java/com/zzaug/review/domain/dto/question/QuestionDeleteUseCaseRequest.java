package com.zzaug.review.domain.dto.question;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class QuestionDeleteUseCaseRequest {
    private Long question_id;
    private Long author_id;
}
