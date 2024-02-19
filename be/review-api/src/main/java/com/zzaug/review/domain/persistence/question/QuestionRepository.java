package com.zzaug.review.domain.persistence.question;

import com.zzaug.review.entity.question.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {
	@Query(
			"UPDATE QuestionEntity q SET q.reviewCnt = q.reviewCnt + 1 WHERE q.questionId = :questionId")
	@Modifying
	void incReviewCnt(Long questionId);

	@Query(
			"UPDATE QuestionEntity q SET q.reviewCnt = q.reviewCnt - 1 WHERE q.questionId = :questionId")
	@Modifying
	void decReviewCnt(Long questionId);
}
