package com.zzaug.review.entity.question;

import java.sql.Timestamp;
import javax.persistence.*;
import lombok.*;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class QuestionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "question_id")
	private Long questionId;

	@Column(length = 10000)
	private String content;

	private String author;

	@Column(name = "author_id")
	private Long authorId;

	@Column(name = "review_cnt")
	private int reviewCnt;

	@Column(name = "created_at")
	private Timestamp createdAt;

	@Column(name = "updated_at")
	private Timestamp updatedAt;
}
