package com.zzaug.review.domain.persistence.review;

import com.zzaug.review.entity.review.ReviewTempEntity;
import org.springframework.data.repository.CrudRepository;

public interface ReviewTempRepository extends CrudRepository<ReviewTempEntity, String> {
}
