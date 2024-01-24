package com.zzaug.review.web.support.usecase;

import com.zzaug.review.domain.dto.comment.CommentCreateUseCaseRequest;
import com.zzaug.review.web.dto.review.CommentRequest;
import com.zzaug.security.authentication.token.TokenUserDetails;
import lombok.experimental.UtilityClass;

import java.sql.Timestamp;

@UtilityClass
public class CommentCreateUseCaseRequestConverter {
//    public static CommentCreateUseCaseRequest from (CommentRequest request, Long questionId, TokenUserDetails userDetails){
//        return CommentCreateUseCaseRequest.builder()
//                .questionId(questionId)
//                .content(request.getContent())
//                .author(userDetails.getUsername())
//                .authorId(Long.valueOf(userDetails.getId()))
//                .parentId(request.getParentId())
//                .createdAt(new Timestamp(System.currentTimeMillis()).toLocalDateTime())
//                .build();
//    }
    public static CommentCreateUseCaseRequest from (CommentRequest request, Long questionId, String name, Long id){
        return CommentCreateUseCaseRequest.builder()
                .questionId(questionId)
                .content(request.getContent())
                .author(name)
                .authorId(id)
                .parentId(request.getParentId())
                .createdAt(new Timestamp(System.currentTimeMillis()).toLocalDateTime())
                .build();
    }
}
