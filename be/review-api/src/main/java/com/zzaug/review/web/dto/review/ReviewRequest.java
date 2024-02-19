package com.zzaug.review.web.dto.review;

import com.zzaug.review.entity.review.ReviewPoint;
import javax.validation.constraints.NotEmpty;

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
public class ReviewRequest {
	@NotEmpty private String content;

	private ReviewPoint startPoint;

	private ReviewPoint endPoint;

	private ReviewType tag;
}
