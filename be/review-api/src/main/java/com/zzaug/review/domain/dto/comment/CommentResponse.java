package com.zzaug.review.domain.dto.comment;

import java.sql.Timestamp;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CommentResponse {
	private Long comment_id;
	private Long question_id;
	private String content;
	private String author;
	private Long author_id;
	private Long parent_id;
	private Timestamp created_at;
	private Timestamp updated_at;
}
