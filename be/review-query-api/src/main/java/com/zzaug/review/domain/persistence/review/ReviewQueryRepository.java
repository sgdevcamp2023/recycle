package com.zzaug.review.domain.persistence.review;

import com.zzaug.review.entity.review.query.ReviewQueryEntity;
import java.util.List;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ReviewQueryRepository extends ElasticsearchRepository<ReviewQueryEntity, Long> {
	List<ReviewQueryEntity> findAllByQuestionIdAndIsDeletedIsFalse(Long questionId);

	List<ReviewQueryEntity> findAllByAuthorIdAndContentContainingAndIsDeletedIsFalse(Long authorId, String query);

	List<ReviewQueryEntity> findAllByAuthorIdAndIsDeletedIsFalse(Long authorId);
}
