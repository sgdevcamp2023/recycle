package com.zzaug.review.domain.persistence.review;

import com.zzaug.review.entity.review.query.ReviewQueryEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ReviewQueryRepository extends ElasticsearchRepository<ReviewQueryEntity, Long> {}
