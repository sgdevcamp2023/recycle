package com.zzaug.review.web.dto.review;

import com.zzaug.review.entity.review.ReviewPoint;
import com.zzaug.review.entity.review.ReviewType;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ReviewTempRequest {
	@NotEmpty
	private String tempId;
	@NotEmpty
	private String content;
	private ReviewPoint startPoint;
	private ReviewPoint endPoint;
	private ReviewType tag;
}
