package com.zzaug.review.domain.persistence.question;

import com.zzaug.review.entity.question.query.QuestionQueryEntity;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;

public interface QuestionQueryRepository
		extends ElasticsearchRepository<QuestionQueryEntity, Long> {
	Page<QuestionQueryEntity> findAllByAuthorIdAndContentContaining(
			Pageable pageable, Long authorId, String query);

	List<QuestionQueryEntity> findAllByAuthorIdAndIsDeletedIsFalse(Long authorId);

	QuestionQueryEntity findByQuestionIdAndIsDeletedIsFalse(Long questionId);

	QuestionQueryEntity findByQuestionIdAndContentContainingAndIsDeletedIsFalse(
			Long questionId, String query);

	Optional<QuestionQueryEntity> findByQuestionId(Long questionId);
}
