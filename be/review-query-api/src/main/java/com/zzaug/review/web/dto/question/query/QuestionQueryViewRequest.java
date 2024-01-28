package com.zzaug.review.web.dto.question.query;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class QuestionViewQueryRequest {
    private Long questionId;

}
