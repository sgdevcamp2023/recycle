package com.zzaug.review.web.dto.review;

import com.zzaug.review.domain.model.review.ReviewType;
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
	private String content;
	private String location;
	private ReviewType tag;
}
