package com.zzaug.review.domain.usecase.review.query;

import com.zzaug.review.config.JpaDataSourceConfig;
import com.zzaug.review.domain.dto.review.query.SearchByQuestionUseCaseRequest;
import com.zzaug.review.domain.persistence.question.QuestionQueryRepository;
import com.zzaug.review.domain.persistence.question.QuestionSearchQueryRepository;
import com.zzaug.review.domain.persistence.review.ReviewQueryRepository;
import com.zzaug.review.entity.question.query.QuestionQueryEntity;
import com.zzaug.review.entity.review.query.ReviewQueryEntity;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchByQuestionUseCase {
	private final QuestionSearchQueryRepository questionQueryRepository;
	private final ReviewQueryRepository reviewQueryRepository;

	@Transactional(JpaDataSourceConfig.TRANSACTION_MANAGER_NAME)
	public List<Map<String, Object>> execute(SearchByQuestionUseCaseRequest request) {
		List<ReviewQueryEntity> reviewResult =
				reviewQueryRepository.findAllByAuthorIdAndIsDeletedIsFalse(request.getAuthorId());
		LinkedHashSet<QuestionQueryEntity> questionResult = new LinkedHashSet<>();

		for (ReviewQueryEntity reviewQueryEntity : reviewResult) {
			Long questionId = reviewQueryEntity.getQuestionId();
			QuestionQueryEntity result =
					questionQueryRepository.findByQuestionIdAndContentContainingAndIsDeletedIsFalse(
							questionId, request.getQuery());
			if (result != null) {
				questionResult.add(result);
			}
		}

		List<Map<String, Object>> responses = new java.util.ArrayList<>();

		for (QuestionQueryEntity question : questionResult) {
			Map<String, Object> questionMap = new java.util.HashMap<>();
			questionMap.put("question", question);
			List<ReviewQueryEntity> relatedReviews =
					reviewResult.stream()
							.filter(
									ReviewQueryEntity ->
											ReviewQueryEntity.getQuestionId().equals(question.getQuestionId()))
							.collect(java.util.stream.Collectors.toList());
			questionMap.put("reviews", relatedReviews);
			responses.add(questionMap);
		}

		return responses;
	}
}
