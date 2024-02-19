package com.zzaug.review.domain.persistence.question;

import com.zzaug.review.entity.question.query.QuestionQueryEntity;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface QuestionQueryRepository
		extends ElasticsearchRepository<QuestionQueryEntity, Long> {
	Optional<QuestionQueryEntity> findByQuestionIdAndIsDeletedIsFalse(Long questionId);

	Page<QuestionQueryEntity> findAllByAuthorIdAndContentContainingAndIsDeletedIsFalse(
			Pageable pageable, Long authorId, String query);
}
