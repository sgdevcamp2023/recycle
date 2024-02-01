package com.zzaug.review.domain.persistence.question;

import com.zzaug.review.entity.question.QuestionRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRequestRepository extends JpaRepository<QuestionRequestEntity, Long>{
    List<QuestionRequestEntity> findAllByReceiverId(Long receiverId);
}
