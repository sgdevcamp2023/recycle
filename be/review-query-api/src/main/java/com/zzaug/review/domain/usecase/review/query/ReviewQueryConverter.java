package com.zzaug.review.domain.usecase.review.query;

import com.zzaug.review.domain.dto.review.query.ReviewQueryUseCaseRequest;
import com.zzaug.review.domain.model.review.query.ReviewQuery;
import com.zzaug.review.entity.review.query.ReviewQueryEntity;
import org.springframework.stereotype.Component;

@Component
public class ReviewQueryConverter {

	public ReviewQuery from(ReviewQueryUseCaseRequest source) {
		return ReviewQuery.builder().name(source.getName()).build();
	}

	public ReviewQuery from(ReviewQueryEntity source) {
		return ReviewQuery.builder().name(source.getId().toString()).build();
	}
}
