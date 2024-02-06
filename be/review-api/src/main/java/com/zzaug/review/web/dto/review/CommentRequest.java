package com.zzaug.review.web.dto.review;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CommentRequest {
	@NotBlank
	private String content;
	private Long parentId;
}
