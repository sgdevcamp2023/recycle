package com.zzaug.review.domain.event.review;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.zzaug.review.entity.review.ReviewPoint;
import com.zzaug.review.entity.review.ReviewType;
import java.time.LocalDateTime;
import javax.persistence.Embedded;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class DeleteReviewEvent {

	private Long reviewId;

	private Long questionId;

	private String author;

	private Long authorId;

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime deletedAt;

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime eventAt;
}
