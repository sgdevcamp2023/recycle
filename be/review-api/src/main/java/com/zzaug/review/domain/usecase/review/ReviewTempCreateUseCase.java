package com.zzaug.review.domain.usecase.review;

import com.zzaug.review.config.JpaDataSourceConfig;
import com.zzaug.review.domain.dto.review.ReviewTempCreateUseCaseRequest;
import com.zzaug.review.domain.model.review.ReviewTemp;
import com.zzaug.review.domain.persistence.review.ReviewTempRepository;
import com.zzaug.review.domain.support.entity.ReviewTempEntityConverter;
import com.zzaug.review.domain.usecase.review.converter.ReviewTempConverter;
import com.zzaug.review.entity.review.ReviewTempEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewTempCreateUseCase {
	private final ReviewTempRepository reviewTempRepository;
	private final ReviewTempConverter reviewTempConverter;

	@Transactional(JpaDataSourceConfig.TRANSACTION_MANAGER_NAME)
	public void execute(ReviewTempCreateUseCaseRequest request) {
		ReviewTemp reviewTemp = reviewTempConverter.from(request);
		ReviewTempEntity reviewTempEntity =
				reviewTempRepository.save(ReviewTempEntityConverter.from(reviewTemp));
	}
}
