package com.zzaug.review.entity.comment;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.*;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CommentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long commentId;

	private Long questionId;

	private String content;

	private String author;

	private Long authorId;

	private Long parentId;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	private boolean isDeleted;

	public void update(String content, LocalDateTime updatedAt) {
		this.content = content;
		this.updatedAt = updatedAt;
	}

	public void deleteComment() {
		this.isDeleted = true;
	}
}
