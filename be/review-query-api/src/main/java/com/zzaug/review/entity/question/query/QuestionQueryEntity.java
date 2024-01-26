package com.zzaug.review.entity.question.query;

import java.time.LocalDateTime;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Document(indexName = "question")
public class QuestionQueryEntity {

	@Id
	private Long questionId;

	@Field(type = FieldType.Text)
	private String content;

	@Field(type = FieldType.Text)
	private String author;

	private Long authorId;

	private int reviewCnt;

	@Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS||epoch_millis")
	private LocalDateTime createdAt;

	@Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS||epoch_millis")
	private LocalDateTime updatedAt;
}
