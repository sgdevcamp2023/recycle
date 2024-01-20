package com.zzaug.review.domain.dto.member;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class MemberResponse {
	private Long questionId;
	private String author;
	private Long authorId;
}
