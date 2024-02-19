package com.zzaug.review.web.dto.review;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ReviewSearchRequest {
	private Long authorId;
	private String query;
}
