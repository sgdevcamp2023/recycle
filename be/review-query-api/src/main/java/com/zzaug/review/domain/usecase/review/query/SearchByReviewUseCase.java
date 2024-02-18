package com.zzaug.review.domain.usecase.review.query;

import com.zzaug.review.config.JpaDataSourceConfig;
import com.zzaug.review.domain.dto.review.query.SearchByReviewUseCaseRequest;
import com.zzaug.review.domain.persistence.question.QuestionQueryRepository;
import com.zzaug.review.domain.persistence.review.ReviewQueryRepository;
import com.zzaug.review.domain.persistence.review.ReviewSearchQueryRepository;
import com.zzaug.review.entity.question.query.QuestionQueryEntity;
import com.zzaug.review.entity.review.query.ReviewQueryEntity;
import java.util.*;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchByReviewUseCase {

	private final ReviewSearchQueryRepository reviewQueryRepository;
	private final QuestionQueryRepository questionQueryRepository;

	@Transactional(JpaDataSourceConfig.TRANSACTION_MANAGER_NAME)
	public List<Map<String, Object>> execute(SearchByReviewUseCaseRequest request) {
		List<ReviewQueryEntity> reviewResult =
				reviewQueryRepository.findAllByAuthorIdAndContentContainingAndIsDeletedIsFalse(
						request.getAuthorId(), request.getQuery());
		LinkedHashSet<QuestionQueryEntity> questionResult = new LinkedHashSet<>();

		for (ReviewQueryEntity reviewQueryEntity : reviewResult) {
			Long questionId = reviewQueryEntity.getQuestionId();
			QuestionQueryEntity result =
					questionQueryRepository.findByQuestionIdAndIsDeletedIsFalse(questionId);
			if (result != null) {
				questionResult.add(result);
			}
		}

		List<Map<String, Object>> responses = new ArrayList<>();

		for (QuestionQueryEntity question : questionResult) {
			Map<String, Object> questionMap = new HashMap<>();
			questionMap.put("question", question);
			List<ReviewQueryEntity> relatedReviews =
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
