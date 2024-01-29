package com.zzaug.review.domain.persistence.review;

import com.zzaug.review.entity.review.ReviewTempEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewTempRepository extends CrudRepository<ReviewTempEntity, String> {

    List<ReviewTempEntity> findAllByAuthorIdAndQuestionId(Long authorId, Long questionId);

    List<ReviewTempEntity> findByTempId(String tempId);
}
