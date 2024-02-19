package com.zzaug.review.domain.persistence.question;

import com.zzaug.review.entity.question.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {}
