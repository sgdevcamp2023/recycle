package com.zzaug.review.domain.persistence.review;

import com.zzaug.review.entity.review.query.ReviewQueryEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewQueryRepository extends ElasticsearchRepository<ReviewQueryEntity, Long> {}
