package com.zzaug.review.domain.dto.review.query;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Setter
public class ReviewQueryDeleteUseCaseRequest {
    private Long reviewId;
    private Long questionId;
    private Long authorId;
}
