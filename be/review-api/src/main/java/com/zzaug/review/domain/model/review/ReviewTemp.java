package com.zzaug.review.domain.model.review;

import com.zzaug.review.entity.review.ReviewPoint;
import java.time.LocalDateTime;

import com.zzaug.review.entity.review.query.ReviewType;
import lombok.*;

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
