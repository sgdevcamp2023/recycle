package com.zzaug.review.domain.persistence.review;

import com.zzaug.review.entity.review.query.ReviewQueryEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewQueryRepository extends ElasticsearchRepository<ReviewQueryEntity, Long> {
    List<ReviewQueryEntity> findAllByQuestionId (Long questionId);
    List<ReviewQueryEntity> findAllByAuthorIdAndContentContaining(Long authorId, String query);
    List<ReviewQueryEntity> findAllByAuthorId(Long authorId);
}
