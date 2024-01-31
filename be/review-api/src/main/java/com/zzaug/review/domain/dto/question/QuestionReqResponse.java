package com.zzaug.review.domain.dto.question;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class QuestionReqResponse {
    private Long questionId;
    private String content;
    private String author;
    private Long authorId;
    private int reviewCnt;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String[] requester;
}
