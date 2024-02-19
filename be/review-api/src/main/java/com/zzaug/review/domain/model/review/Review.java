package com.zzaug.review.domain.model.review;

import com.zzaug.review.entity.review.ReviewPoint;
import java.time.LocalDateTime;
import javax.persistence.Embedded;

import com.zzaug.review.entity.review.query.ReviewType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Review {

	private Long reviewId;

	private Long questionId;

	private String content;

	private String author;

	private Long authorId;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	@Embedded private ReviewPoint startPoint;

	@Embedded private ReviewPoint endPoint;

	private ReviewType tag;

	private boolean isDeleted;
}
