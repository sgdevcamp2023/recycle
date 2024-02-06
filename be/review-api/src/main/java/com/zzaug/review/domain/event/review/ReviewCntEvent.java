package com.zzaug.review.domain.event.review;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.time.LocalDateTime;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ReviewCntEvent {
	private Long questionId;
	private String content;
	private String author;
	private Long authorId;
	private int reviewCnt;

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime createdAt;

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime updatedAt;
}
