package com.zzaug.review.domain.event.review;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
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
public class EditReviewEvent {
    private Long reviewId;

    private Long questionId;

    private String content;

    private String author;

    private Long authorId;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdAt;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime updatedAt;

    @Embedded
    private ReviewPoint startPoint;

    @Embedded private ReviewPoint endPoint;

    private ReviewType tag;
}
