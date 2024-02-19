package com.zzaug.review.domain.usecase.review.query;


import com.zzaug.review.config.JpaDataSourceConfig;
import com.zzaug.review.domain.dto.review.SearchByReviewUseCaseRequest;
import com.zzaug.review.domain.persistence.question.QuestionRepository;
import com.zzaug.review.domain.persistence.review.ReviewRepository;
import com.zzaug.review.entity.question.QuestionEntity;

import com.zzaug.review.entity.review.ReviewEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchByReviewUseCase {

	private final ReviewRepository reviewQueryRepository;
	private final QuestionRepository questionQueryRepository;

	@Transactional(JpaDataSourceConfig.TRANSACTION_MANAGER_NAME)
	public List<Map<String, Object>> execute(SearchByReviewUseCaseRequest request) {
		List<ReviewEntity> reviewResult =
				reviewQueryRepository.findAllByAuthorIdAndContentContainingAndIsDeletedIsFalse(
						request.getAuthorId(), request.getQuery());
		LinkedHashSet<QuestionEntity> questionResult = new LinkedHashSet<>();

		for (ReviewEntity reviewQueryEntity : reviewResult) {
			Long questionId = reviewQueryEntity.getQuestionId();
			QuestionEntity result =
					questionQueryRepository.findByQuestionIdAndIsDeletedIsFalse(questionId).orElseThrow();
			if (result != null) {
				questionResult.add(result);
			}
		}

		List<Map<String, Object>> responses = new ArrayList<>();

		for (QuestionEntity question : questionResult) {
			Map<String, Object> questionMap = new HashMap<>();
			questionMap.put("question", question);
			List<ReviewEntity> relatedReviews =
					reviewResult.stream()
							.filter(
									ReviewQueryEntity ->
											ReviewQueryEntity.getQuestionId().equals(question.getQuestionId()))
							.collect(Collectors.toList());
			questionMap.put("reviews", relatedReviews);
			responses.add(questionMap);
		}

		return responses;
	}
}
