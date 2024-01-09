package com.zzaug.review.domain.support.entity;

import com.zzaug.review.domain.model.review.query.ReviewQuery;
import com.zzaug.review.entity.review.query.ReviewQueryEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ReviewQueryEntityConverter {

	public static ReviewQueryEntity from(ReviewQuery source) {
		return ReviewQueryEntity.builder().build();
	}
}
