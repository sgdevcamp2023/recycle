package com.zzaug.review.entity.review;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@RedisHash(value = "review_temp", timeToLive = 86400)
public class ReviewTempEntity {

    @Id
    @Indexed
    private String tempId;
    @Indexed
    private Long questionId;
    @Indexed
    private String content;
    @Indexed
    private String author;
    @Indexed
    private Long authorId;
    @Indexed
    private LocalDateTime createdAt;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "point", column = @Column(name = "start_point_point")),
            @AttributeOverride(name = "index", column = @Column(name = "start_point_index"))
    })
    @Indexed
    private ReviewPoint startPoint;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "point", column = @Column(name = "end_point_point")),
            @AttributeOverride(name = "index", column = @Column(name = "end_point_index"))
    })
    @Indexed
    private ReviewPoint endPoint;
    @Indexed
    private ReviewType tag;
}
