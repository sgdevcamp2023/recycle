package com.zzaug.review.domain.model.review.query;

import com.zzaug.review.entity.review.query.ReviewPoint;
import com.zzaug.review.entity.review.query.ReviewType;
import java.time.LocalDateTime;
import javax.persistence.*;
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
public class ReviewQuery {

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

	private Boolean isDeleted;
}
