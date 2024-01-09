package com.zzaug.review.domain.support.entity;

import com.zzaug.review.domain.model.review.Review;
import com.zzaug.review.entity.review.ReviewEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ReviewEntityConverter {

	public static ReviewEntity from(Review source) {
		return ReviewEntity.builder().build();
	}
}
