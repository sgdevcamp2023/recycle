package com.zzaug.review.domain.usecase.review.converter;

import com.zzaug.review.domain.dto.review.ReviewTempCreateUseCaseRequest;
import com.zzaug.review.domain.model.review.ReviewTemp;
import org.springframework.stereotype.Component;

@Component
public class ReviewTempConverter {

//    public static ReviewTempEntity from (ReviewTemp source){
//        return ReviewTempEntity.builder()
//                .t_id(source.getT_id())
//                .content(source.getContent())
//                .author(source.getAuthor())
//                .author_id(source.getAuthor_id())
//                .created_at(source.getCreated_at())
//                .start_point(source.getStart_point())
//                .end_point(source.getEnd_point())
//                .tag(source.getTag())
//                .build();
//    }

    public ReviewTemp from (ReviewTempCreateUseCaseRequest source){
        return ReviewTemp.builder()
                .tId(source.getTId())
                .questionId(source.getQuestionId())
                .content(source.getContent())
                .author(source.getAuthor())
                .authorId(source.getAuthorId())
                .createdAt(source.getCreatedAt())
                .startPoint(source.getStartPoint())
                .endPoint(source.getEndPoint())
                .tag(source.getTag())
                .build();
    }
}
