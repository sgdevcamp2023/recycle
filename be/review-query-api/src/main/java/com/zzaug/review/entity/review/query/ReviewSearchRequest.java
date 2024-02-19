package com.zzaug.review.web.dto.review.query;

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
