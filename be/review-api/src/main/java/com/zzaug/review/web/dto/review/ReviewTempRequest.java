package com.zzaug.review.web.dto.review;

import com.zzaug.review.domain.model.review.ReviewType;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ReviewTempRequest {
	private String t_id;
	private String content;
	private String location;
	private ReviewType tag;
}
