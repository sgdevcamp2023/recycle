package com.zzaug.review.domain.persistence.question;

import com.zzaug.review.entity.question.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {}
