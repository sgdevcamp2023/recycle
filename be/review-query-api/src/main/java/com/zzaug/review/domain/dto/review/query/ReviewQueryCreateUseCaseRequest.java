package com.zzaug.review.domain.dto.review.query;

import com.zzaug.review.entity.review.query.ReviewPoint;
import com.zzaug.review.entity.review.query.ReviewType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ReviewQueryCreateUseCaseRequest {
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
