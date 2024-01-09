package com.zzaug.review.domain.usecase.review;

import com.zzaug.review.domain.dto.review.ReviewUseCaseRequest;
import com.zzaug.review.domain.model.review.Review;
import com.zzaug.review.entity.review.ReviewEntity;
import org.springframework.stereotype.Component;

@Component
public class ReviewConverter {

	public Review from(ReviewUseCaseRequest source) {
		return Review.builder().name(source.getName()).build();
	}

	public Review from(ReviewEntity source) {
		return Review.builder().name(source.getId().toString()).build();
	}
}
