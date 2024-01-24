package com.zzaug.review.web.dto.review;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CommentRequest {
    private String content;
    private Long parentId;
}
