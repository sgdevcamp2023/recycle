package com.zzaug.review.domain.model.question;

import lombok.*;

import javax.persistence.Column;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class QuestionRequest {
    private Long questionReqId;
    private String requester;
    private Long requesterId;
}
