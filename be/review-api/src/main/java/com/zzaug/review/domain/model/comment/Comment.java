package com.zzaug.review.domain.model.comment;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Comment {
    private Long commentId;

    private Long questionId;

    private String content;

    private String author;

    private Long authorId;

    private Long parentId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
