package com.zzaug.review.entity.question;

import java.sql.Timestamp;
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
public class QuestionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long question_id;

	private String content;

	private String author;

	private Long author_id;

	private int review_cnt;

	private Timestamp created_at;

	private Timestamp updated_at;
}
