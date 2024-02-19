package com.zzaug.review.domain.usecase.review.query;

import com.zzaug.review.config.JpaDataSourceConfig;
import com.zzaug.review.domain.dto.review.SearchByQuestionUseCaseRequest;
import com.zzaug.review.domain.persistence.question.QuestionRepository;
import com.zzaug.review.domain.persistence.review.ReviewRepository;
import com.zzaug.review.entity.question.QuestionEntity;
import com.zzaug.review.entity.review.ReviewEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchByQuestionUseCase {
	private final QuestionRepository questionQueryRepository;
	private final ReviewRepository reviewQueryRepository;

	@Transactional(JpaDataSourceConfig.TRANSACTION_MANAGER_NAME)
	public List<Map<String, Object>> execute(SearchByQuestionUseCaseRequest request) {
		List<ReviewEntity> reviewResult =
				reviewQueryRepository.findAllByAuthorIdAndIsDeletedIsFalse(request.getAuthorId());
		LinkedHashSet<QuestionEntity> questionResult = new LinkedHashSet<>();

		for (ReviewEntity reviewQueryEntity : reviewResult) {
			Long questionId = reviewQueryEntity.getQuestionId();
			QuestionEntity result =
					questionQueryRepository.findByQuestionIdAndContentContainingAndIsDeletedIsFalse(
							questionId, request.getQuery());
			if (result != null) {
				questionResult.add(result);
			}
		}

		List<Map<String, Object>> responses = new java.util.ArrayList<>();

		for (QuestionEntity question : questionResult) {
			Map<String, Object> questionMap = new java.util.HashMap<>();
			questionMap.put("question", question);
			List<ReviewEntity> relatedReviews =
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
