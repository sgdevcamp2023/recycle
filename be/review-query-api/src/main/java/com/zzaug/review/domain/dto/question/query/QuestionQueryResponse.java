package com.zzaug.review.domain.dto.question.query;

import lombok.*;

import java.sql.Timestamp;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class QuestionQueryResponse {
    private Long question_id;
    private String content;
    private String author;
    private Long author_id;
    private int review_cnt;
    private Timestamp created_at;
    private Timestamp updated_at;
}
