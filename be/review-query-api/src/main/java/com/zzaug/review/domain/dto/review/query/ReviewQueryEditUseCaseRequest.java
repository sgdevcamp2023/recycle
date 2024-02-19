package com.zzaug.review.domain.dto.review.query;

import com.zzaug.review.entity.review.query.ReviewPoint;
import com.zzaug.review.entity.review.query.ReviewType;
import java.time.LocalDateTime;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ReviewQueryEditUseCaseRequest {
	private Long reviewId;
	private Long questionId;
	private String content;
	private String author;
	private Long authorId;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private ReviewPoint startPoint;
	private ReviewPoint endPoint;
	private ReviewType tag;
}
