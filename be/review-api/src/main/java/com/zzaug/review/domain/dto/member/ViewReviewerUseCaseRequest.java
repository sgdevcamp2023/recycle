package com.zzaug.review.domain.dto.member;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ViewReviewerUseCaseRequest {
    private Long questionId;
}
