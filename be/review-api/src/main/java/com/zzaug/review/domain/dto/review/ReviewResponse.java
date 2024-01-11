package com.zzaug.review.domain.dto.review;

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
public class ReviewResponse {

	private Long review_id;
	private Long question_id;
	private String content;
	private String location;
	private String author;
	private Long author_id;
	private Timestamp created_at;
	private Timestamp updated_at;
	private String tag;
}
