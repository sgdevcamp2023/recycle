package com.zzaug.review.domain.dto.member;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class MemberResponse {
	private Long question_id;
	private String author;
	private Long author_id;
}
