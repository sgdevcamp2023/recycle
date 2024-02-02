package com.zzaug.review.domain.model.review.query;

import com.zzaug.review.entity.review.query.ReviewPoint;
import com.zzaug.review.entity.review.query.ReviewType;
import java.sql.Timestamp;
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
	private ReviewPoint startPoint;
	private ReviewPoint endPoint;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private ReviewType tag;
}
