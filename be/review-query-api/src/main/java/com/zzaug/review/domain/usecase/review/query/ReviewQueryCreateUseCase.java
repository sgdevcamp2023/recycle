package com.zzaug.review.domain.usecase.review.query;

import com.zzaug.review.domain.dto.review.query.ReviewQueryResponse;
import com.zzaug.review.domain.dto.review.query.ReviewQueryUseCaseRequest;
import com.zzaug.review.domain.model.review.query.ReviewQuery;
import com.zzaug.review.domain.persistence.review.ReviewQueryRepository;
import com.zzaug.review.domain.support.entity.ReviewQueryEntityConverter;
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
public class ReviewQueryUseCase {

	private final ReviewQueryRepository reviewQueryRepository;
	private final ReviewQueryConverter reviewQueryConverter;

	@Transactional
	public ReviewQueryResponse execute(ReviewQueryUseCaseRequest request) {
		ReviewQuery review = reviewQueryConverter.from(request);

		reviewQueryRepository.save(ReviewQueryEntityConverter.from(review));

		List<ReviewQuery> all =
				reviewQueryRepository.findAll().stream()
						.map(reviewQueryConverter::from)
						.collect(Collectors.toList());

		UtilSomething.a("a");
		UtilSomething.b("b");
		UtilSomething.c("c");

		return ReviewQueryResponse.builder().build();
	}
}
