package com.zzaug.review.domain.persistence.comment;

import com.zzaug.review.entity.comment.CommentEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
	List<CommentEntity> findAllByQuestionId(Long questionId);
}
