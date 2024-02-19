package com.zzaug.review.entity.question;

import java.time.LocalDateTime;
import javax.persistence.Column;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@RedisHash(value = "question_temp", timeToLive = 86400)
public class QuestionTempEntity {

	@Id
	@Indexed
	@Column(name = "temp_id")
	private String tempId;

	@Indexed private String content;

	@Indexed private String author;

	@Indexed
	@Column(name = "author_id")
	private Long authorId;

	@Indexed
	@Column(name = "created_at")
	private LocalDateTime createdAt;
}
