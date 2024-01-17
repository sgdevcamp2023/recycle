package com.zzaug.review.entity.question;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@RedisHash(value = "question_temp", timeToLive = 86400)
public class QuestionTempEntity {

    @Id
    @Indexed
    private String t_id;

    @Indexed
    private String content;

    @Indexed
    private String author;

    @Indexed
    private Long author_id;

    @Indexed
    private LocalDateTime created_at;
}
