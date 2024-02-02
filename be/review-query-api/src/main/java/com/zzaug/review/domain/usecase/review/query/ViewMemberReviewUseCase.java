package com.zzaug.review.domain.usecase.review.query;

import com.zzaug.review.domain.dto.review.query.ViewMemberReviewUseCaseRequest;
import com.zzaug.review.domain.persistence.question.QuestionQueryRepository;
import com.zzaug.review.domain.persistence.review.ReviewQueryRepository;
import com.zzaug.review.entity.question.query.QuestionQueryEntity;
import com.zzaug.review.entity.review.query.ReviewQueryEntity;
import java.util.*;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ViewMemberReviewUseCase {
	private final ReviewQueryRepository reviewQueryRepository;
	private final QuestionQueryRepository questionQueryRepository;

	@Transactional
	public List<Map<String, Object>> execute(ViewMemberReviewUseCaseRequest request) {
		List<ReviewQueryEntity> reviewResult =
				reviewQueryRepository.findAllByAuthorId(request.getAuthorId());
		LinkedHashSet<QuestionQueryEntity> questionResult = new LinkedHashSet<>();

		for (ReviewQueryEntity reviewQueryEntity : reviewResult) {
			Long questionId = reviewQueryEntity.getQuestionId();
			QuestionQueryEntity result = questionQueryRepository.findByQuestionId(questionId);
			questionResult.add(result);
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
