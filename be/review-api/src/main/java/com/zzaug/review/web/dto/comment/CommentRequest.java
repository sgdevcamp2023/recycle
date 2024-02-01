package com.zzaug.review.web.dto.comment;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CommentRequest {
	private Long questionId;
	private String content;
	private Long parentId;
}
