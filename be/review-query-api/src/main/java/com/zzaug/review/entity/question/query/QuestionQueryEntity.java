package com.zzaug.review.entity.question.query;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Document(indexName = "question")
@Mapping(mappingPath = "elasticsearch/mappings/question-mapping.json")
@Setting(settingPath = "elasticsearch/settings/question-setting.json")
public class QuestionQueryEntity {

	@Id private Long questionId;

	@Field(type = FieldType.Text)
	private String content;

	@Field(type = FieldType.Text)
	private String author;

	private Long authorId;

	private int reviewCnt;

	@Field(
			type = FieldType.Date,
			format = DateFormat.custom,
			pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS||epoch_millis")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdAt;

	@Field(
			type = FieldType.Date,
			format = DateFormat.custom,
			pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS||epoch_millis")
	private LocalDateTime deletedAt;

	@ColumnDefault("false")
	private boolean isDeleted;

	@Override
	public int hashCode() {
		return questionId.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof QuestionQueryEntity) {
			return questionId.equals(((QuestionQueryEntity) obj).questionId);
		}
		return false;
	}
	public void incReviewCnt() {
		this.reviewCnt++;
	}

	public void decReviewCnt() {
		this.reviewCnt--;
	}
}
