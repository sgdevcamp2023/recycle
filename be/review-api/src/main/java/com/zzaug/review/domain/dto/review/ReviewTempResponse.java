package com.zzaug.review.domain.dto.review;

import com.zzaug.review.domain.model.review.ReviewType;
import java.sql.Timestamp;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ReviewTempResponse {
	private String t_id;
	private Long question_id;
	private String content;
	private String location;
	private String author;
	private Long author_id;
	private Timestamp created_at;
	private Timestamp updated_at;
	private ReviewType tag;
}
