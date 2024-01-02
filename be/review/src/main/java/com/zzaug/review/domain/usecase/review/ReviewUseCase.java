package com.zzaug.review.domain.usecase.review;

import com.zzaug.review.domain.dto.review.ReviewResponse;
import com.zzaug.review.domain.dto.review.ReviewUseCaseRequest;
import com.zzaug.review.domain.model.review.Review;
import com.zzaug.review.domain.persistence.review.ReviewRepository;
import com.zzaug.review.domain.support.entity.ReviewEntityConverter;
import com.zzaug.review.domain.util.UtilSomething;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewUseCase {

	private final ReviewRepository reviewRepository;
	private final ReviewConverter reviewConverter;

	@Transactional
	public ReviewResponse execute(ReviewUseCaseRequest request) {
		Review review = reviewConverter.from(request);

		reviewRepository.save(ReviewEntityConverter.from(review));

		List<Review> all =
				reviewRepository.findAll().stream().map(reviewConverter::from).collect(Collectors.toList());

		UtilSomething.a("a");
		UtilSomething.b("b");
		UtilSomething.c("c");

		return ReviewResponse.builder().build();
	}
}
