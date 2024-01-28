package com.zzaug.review.domain.persistence.question;

import com.zzaug.review.entity.question.query.QuestionQueryEntity;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.time.LocalDateTime;

public interface QuestionQueryRepository
		extends ElasticsearchRepository<QuestionQueryEntity, Long> {
}