package com.zzaug.review.domain.persistence.review;

import com.zzaug.review.entity.review.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {

    List<ReviewEntity> findAllByQuestionIdAndIsDeletedIsFalse(Long questionId);
    List<ReviewEntity> findAllByAuthorIdAndIsDeletedIsFalse(Long authorId);

    List<ReviewEntity> findAllByAuthorIdAndContentContainingAndIsDeletedIsFalse (Long authorId, String query);
}
