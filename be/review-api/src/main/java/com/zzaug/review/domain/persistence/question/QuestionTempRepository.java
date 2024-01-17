package com.zzaug.review.domain.persistence.question;

import com.zzaug.review.entity.question.QuestionTempEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuestionTempRepository extends CrudRepository<QuestionTempEntity, String> {
    List<QuestionTempEntity> findAllByContent(String content);
}
