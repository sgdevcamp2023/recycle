package com.zzaug.review.domain.dto.review;

import com.zzaug.review.entity.review.ReviewPoint;
import com.zzaug.review.entity.review.ReviewType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Embedded;
import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ReviewCreateUseCaseRequest {

	private Long reviewId;

	private Long questionId;

	private String content;

	private String author;

	private Long authorId;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	@Embedded
	private ReviewPoint startPoint;

	@Embedded
	private ReviewPoint endPoint;

	private ReviewType tag;

}
