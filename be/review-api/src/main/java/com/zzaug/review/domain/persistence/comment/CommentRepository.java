package com.zzaug.review.domain.persistence.comment;

import com.zzaug.review.entity.comment.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findAllByQuestionId (Long questionId);
}
