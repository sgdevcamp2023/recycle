package com.zzaug.review.entity.review.query;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Document(indexName = "review")
@Mapping(mappingPath = "elasticsearch/mappings/review-query-mapping.json")
public class ReviewQueryEntity {

	@Id
	private Long reviewId;

	private Long questionId;

	private String content;

	private String author;

	private Long authorId;

	@Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS||epoch_millis")
	private LocalDateTime createdAt;

	@Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS||epoch_millis")
	private LocalDateTime updatedAt;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "point", column = @Column(name = "start_point_point")),
			@AttributeOverride(name = "index", column = @Column(name = "start_point_index"))
	})
	private ReviewPoint startPoint;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "point", column = @Column(name = "end_point_point")),
			@AttributeOverride(name = "index", column = @Column(name = "end_point_index"))
	})
	private ReviewPoint endPoint;

	private ReviewType tag;


}
