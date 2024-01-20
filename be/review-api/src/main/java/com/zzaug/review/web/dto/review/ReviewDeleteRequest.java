package com.zzaug.review.web.dto.review;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ReviewDeleteRequest {
    private Long reviewId;
    private Long questionId;
    private Long authorId;
}
