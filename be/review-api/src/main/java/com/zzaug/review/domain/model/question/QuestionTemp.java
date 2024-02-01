package com.zzaug.review.domain.model.question;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class QuestionTemp {

    private String tempId;

    private String content;

    private String author;

    private Long authorId;

    private LocalDateTime createdAt;

}
