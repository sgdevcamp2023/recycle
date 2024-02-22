package com.zzaug.review.web.dto.review;

import javax.validation.constraints.NotBlank;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CommentRequest {
	@NotBlank private String content;
	private Long parentId;
}
