package com.zzaug.review.entity.question;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.Column;
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
    @Column(name = "t_id")
    private String tId;

    @Indexed
    private String content;

    @Indexed
    private String author;

    @Indexed
    @Column(name = "author_id")
    private Long authorId;

    @Indexed
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
