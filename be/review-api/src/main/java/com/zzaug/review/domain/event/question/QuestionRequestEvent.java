package com.zzaug.review.domain.event.question;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class QuestionRequestEvent {
    private Long questionReqId;
    private String requester;
    private Long requesterId;
}
