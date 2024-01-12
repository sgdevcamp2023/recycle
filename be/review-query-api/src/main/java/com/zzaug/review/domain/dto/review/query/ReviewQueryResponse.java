package com.zzaug.review.domain.dto.review.query;

import com.zzaug.review.domain.model.review.ReviewType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ReviewQueryResponse {

	private Long review_id;
	private Long question_id;
	private String content;
	private String location;
	private String author;
	private Long author_id;
	private Timestamp created_at;
	private Timestamp updated_at;
	private ReviewType tag;
}
