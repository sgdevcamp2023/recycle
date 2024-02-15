package com.zzaug.review.entity.review.query;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Document(indexName = "review")
@Mapping(mappingPath = "elasticsearch/mappings/review-mapping.json")
@Setting(settingPath = "elasticsearch/settings/review-setting.json")
public class ReviewQueryEntity {

	@Id private Long reviewId;

	private Long questionId;

	private String content;

	private String author;

	private Long authorId;

	@Field(
			type = FieldType.Date,
			format = DateFormat.custom,
			pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS||epoch_millis")
	private LocalDateTime createdAt;

	@Field(
			type = FieldType.Date,
			format = DateFormat.custom,
			pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS||epoch_millis")
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

    private boolean isDeleted;
}
