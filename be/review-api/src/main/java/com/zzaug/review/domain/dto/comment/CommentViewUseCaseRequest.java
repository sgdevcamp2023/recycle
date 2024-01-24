package com.zzaug.review.domain.dto.comment;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CommentViewUseCaseRequest {
    private Long questionId;
}
