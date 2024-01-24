package com.zzaug.review.web.support.usecase;

import com.zzaug.review.domain.dto.comment.CommentEditUseCaseRequest;
import com.zzaug.review.web.dto.review.CommentRequest;
import com.zzaug.security.authentication.token.TokenUserDetails;
import lombok.experimental.UtilityClass;

import java.sql.Timestamp;

@UtilityClass
public class CommentEditUseCaseRequestConverter {
//    public static CommentEditUseCaseRequest from (CommentRequest request, Long commentId, Long questionId, TokenUserDetails userDetails){
//        return CommentEditUseCaseRequest.builder()
//                .commentId(commentId)
//                .questionId(questionId)
//                .content(request.getContent())
//                .author(userDetails.getUsername())
//                .authorId(Long.valueOf(userDetails.getId()))
//                .parentId(request.getParentId())
//                .updatedAt(new Timestamp(System.currentTimeMillis()).toLocalDateTime())
//                .build();
//    }

    public static CommentEditUseCaseRequest from (CommentRequest request, Long commentId, Long questionId, String name, Long id){
        return CommentEditUseCaseRequest.builder()
                .commentId(commentId)
                .questionId(questionId)
                .content(request.getContent())
                .author(name)
                .authorId(id)
                .parentId(request.getParentId())
                .updatedAt(new Timestamp(System.currentTimeMillis()).toLocalDateTime())
                .build();
    }
}
