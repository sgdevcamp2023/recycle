package com.zzaug.review.domain.dto.review;

import com.zzaug.review.entity.review.ReviewPoint;
import java.time.LocalDateTime;
import javax.persistence.Embedded;

import com.zzaug.review.entity.review.query.ReviewType;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ReviewTempResponse {
	private String tempId;

	private Long questionId;

	private String content;

	private String author;

	private Long authorId;

	private LocalDateTime createdAt;

	@Embedded private ReviewPoint startPoint;

	@Embedded private ReviewPoint endPoint;

	private ReviewType tag;
}
