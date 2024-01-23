package com.zzaug.review.web.dto.comment;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CommentRequest {
	private Long question_id;
	private String content;
	private Long parent_id;
}
