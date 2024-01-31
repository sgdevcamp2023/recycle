package com.zzaug.review.domain.persistence.member;

import com.zzaug.review.entity.member.ReviewerListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewerListRepository extends JpaRepository<ReviewerListEntity, Long>{
    List<ReviewerListEntity> findAllByQuestionId(Long questionId);
}
