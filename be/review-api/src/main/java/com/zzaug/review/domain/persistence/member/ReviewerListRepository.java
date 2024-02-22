package com.zzaug.review.domain.persistence.member;

import com.zzaug.review.entity.member.ReviewerListEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewerListRepository extends JpaRepository<ReviewerListEntity, Long> {
	List<ReviewerListEntity> findAllByQuestionId(Long questionId);

	boolean existsByReviewerIdAndQuestionId(Long reviewerId, Long questionId);

	void deleteByReviewerIdAndQuestionId(Long reviewerId, Long questionId);
}
