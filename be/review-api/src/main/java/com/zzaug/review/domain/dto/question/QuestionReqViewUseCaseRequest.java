package com.zzaug.review.domain.dto.question;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class QuestionReqViewUseCaseRequest {
    private Long receiverId;
}
