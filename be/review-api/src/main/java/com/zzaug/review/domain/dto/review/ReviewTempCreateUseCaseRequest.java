package com.zzaug.review.domain.dto.review;

import com.zzaug.review.entity.review.ReviewPoint;
import com.zzaug.review.entity.review.ReviewType;
import lombok.*;

import javax.persistence.Embedded;
import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ReviewTempCreateUseCaseRequest {
    private String tempId;

    private Long questionId;

    private String content;

    private String author;

    private Long authorId;

    private LocalDateTime createdAt;

    @Embedded
    private ReviewPoint startPoint;

    @Embedded
    private ReviewPoint endPoint;

    private ReviewType tag;
}
