package com.zzaug.review.domain.dto.member;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class MemberResponse {

	private String reviewerName;
	private Long reviewerId;
}
