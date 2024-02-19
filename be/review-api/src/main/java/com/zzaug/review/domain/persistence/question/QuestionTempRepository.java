package com.zzaug.review.domain.persistence.question;

import com.zzaug.review.entity.question.QuestionTempEntity;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionTempRepository extends CrudRepository<QuestionTempEntity, String> {

	List<QuestionTempEntity> findAllByAuthorId(Long authorId);

	List<QuestionTempEntity> findByTempId(String tId);
}
