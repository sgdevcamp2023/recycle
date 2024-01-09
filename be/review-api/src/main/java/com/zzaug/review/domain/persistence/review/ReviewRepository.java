package com.zzaug.review.domain.persistence.review;

import com.zzaug.review.entity.review.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {}
