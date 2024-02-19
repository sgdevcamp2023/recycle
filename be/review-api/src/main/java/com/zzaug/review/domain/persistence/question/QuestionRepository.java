package com.zzaug.review.domain.persistence.question;

import com.zzaug.review.entity.question.QuestionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {
	@Query(
			"UPDATE QuestionEntity q SET q.reviewCnt = q.reviewCnt + 1 WHERE q.questionId = :questionId")
	@Modifying
	void incReviewCnt(Long questionId);

	@Query(
			"UPDATE QuestionEntity q SET q.reviewCnt = q.reviewCnt - 1 WHERE q.questionId = :questionId")
	@Modifying
	void decReviewCnt(Long questionId);

	Page<QuestionEntity> findAllByAuthorIdAndContentContainingAndIsDeletedIsFalse (
			Pageable pageRequest, Long authorId, String query);

	Optional<QuestionEntity> findByQuestionIdAndIsDeletedIsFalse(Long questionId);

	QuestionEntity findByQuestionIdAndContentContainingAndIsDeletedIsFalse (
			Long questionId, String query);
}
