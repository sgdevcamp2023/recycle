package com.zzaug.review.domain.dto.comment;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CommentDeleteUseCaseRequest {
    private Long commentId;
    private Long questionId;
    private Long authorId;
}
