package com.zzaug.review.domain.persistence.question;

import com.zzaug.review.entity.question.query.QuestionQueryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface QuestionQueryRepository
		extends ElasticsearchRepository<QuestionQueryEntity, Long> {
	Page<QuestionQueryEntity> findAllByAuthorIdAndContentContaining(
			Pageable pageable, Long authorId, String query);

	QuestionQueryEntity findByQuestionIdAndIsDeletedIsFalse(Long questionId);

	QuestionQueryEntity findByQuestionIdAndContentContainingAndIsDeletedIsFalse(Long questionId, String query);
}