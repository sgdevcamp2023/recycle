package com.zzaug.review.domain.persistence.review;

import com.zzaug.review.entity.review.ReviewTempEntity;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ReviewTempRepository extends CrudRepository<ReviewTempEntity, String> {

	List<ReviewTempEntity> findAllByAuthorIdAndQuestionId(Long authorId, Long questionId);

	List<ReviewTempEntity> findByTempId(String tempId);
}
