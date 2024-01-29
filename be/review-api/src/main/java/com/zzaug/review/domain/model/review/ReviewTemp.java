package com.zzaug.review.domain.model.review;

import com.zzaug.review.entity.review.ReviewPoint;
import com.zzaug.review.entity.review.ReviewType;
import lombok.*;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ReviewTemp {
    private String tempId;
    private Long questionId;
    private String content;
    private String author;
    private Long authorId;
    private LocalDateTime createdAt;
    private ReviewPoint startPoint;
    private ReviewPoint endPoint;
    private ReviewType tag;
}
