package com.zzaug.review.domain.usecase.review.query;

import com.zzaug.review.config.JpaDataSourceConfig;
import com.zzaug.review.domain.dto.review.ReviewByQuestionUseCaseRequest;
import com.zzaug.review.domain.dto.review.ReviewResponse;

import com.zzaug.review.domain.persistence.review.ReviewRepository;
import com.zzaug.review.domain.usecase.review.converter.ReviewResponseConverter;
import com.zzaug.review.entity.review.ReviewEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewByQuestionUseCase {
	private final ReviewRepository reviewQueryRepository;

	@Transactional(JpaDataSourceConfig.TRANSACTION_MANAGER_NAME)
	public List<ReviewResponse> execute(ReviewByQuestionUseCaseRequest request) {
		List<ReviewEntity> result =
				reviewQueryRepository.findAllByQuestionIdAndIsDeletedIsFalse(request.getQuestionId());
		return result.stream().map(ReviewResponseConverter::from).collect(Collectors.toList());
	}
}
