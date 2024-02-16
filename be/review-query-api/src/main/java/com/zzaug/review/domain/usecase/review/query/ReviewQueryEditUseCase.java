package com.zzaug.review.domain.usecase.review.query;

import com.zzaug.review.config.JpaDataSourceConfig;
import com.zzaug.review.domain.event.review.EditReviewEvent;
import com.zzaug.review.domain.model.review.query.ReviewQuery;
import com.zzaug.review.domain.persistence.review.ReviewQueryRepository;
import com.zzaug.review.domain.support.entity.ReviewQueryEntityConverter;
import com.zzaug.review.entity.review.query.ReviewQueryEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewQueryEditUseCase {
	private final ReviewQueryRepository reviewQueryRepository;
	private final ReviewQueryConverter reviewQueryConverter;

	@Transactional(JpaDataSourceConfig.TRANSACTION_MANAGER_NAME)
	@EventListener
	public void execute(EditReviewEvent event) {
		ReviewQueryEntity reviewQueryEntity =
				reviewQueryRepository.findById(event.getReviewId()).orElseThrow(()-> new NoSuchElementException("Review not found"));
		ReviewQuery review = reviewQueryConverter.from(event, reviewQueryEntity);
		reviewQueryRepository.save(ReviewQueryEntityConverter.from(review));
	}
}
