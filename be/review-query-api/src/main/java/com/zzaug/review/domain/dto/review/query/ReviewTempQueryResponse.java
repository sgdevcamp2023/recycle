package com.zzaug.review.domain.dto.review.query;

import com.zzaug.review.entity.review.query.ReviewType;
import java.sql.Timestamp;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ReviewTempQueryResponse {
	private String tId;
	private Long questionId;
	private String content;
	private String location;
	private String author;
	private Long authorId;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private ReviewType tag;
}
