package com.zzaug.review.domain.usecase.review.query;

import com.zzaug.review.config.JpaDataSourceConfig;
import com.zzaug.review.domain.event.review.DeleteReviewEvent;
import com.zzaug.review.domain.model.review.query.ReviewQuery;
import com.zzaug.review.domain.persistence.question.QuestionQueryRepository;
import com.zzaug.review.domain.persistence.review.ReviewQueryRepository;
import com.zzaug.review.domain.support.entity.ReviewQueryEntityConverter;
import com.zzaug.review.entity.question.query.QuestionQueryEntity;
import com.zzaug.review.entity.review.query.ReviewQueryEntity;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewQueryDeleteUseCase {
	private final ReviewQueryRepository reviewQueryRepository;
	private final QuestionQueryRepository questionQueryRepository;
	private final ReviewQueryConverter reviewQueryConverter;

	@Transactional(JpaDataSourceConfig.TRANSACTION_MANAGER_NAME)
	@EventListener
	public void execute(DeleteReviewEvent event) {
		QuestionQueryEntity parentQuestion =
				questionQueryRepository
						.findById(event.getQuestionId())
						.orElseThrow(() -> new NoSuchElementException("Question not found"));
		ReviewQueryEntity target =
				reviewQueryRepository
						.findById(event.getReviewId())
						.orElseThrow(() -> new NoSuchElementException("Review not found"));
		ReviewQuery review = reviewQueryConverter.from(event, target);
		reviewQueryRepository.save(ReviewQueryEntityConverter.from(review));

		parentQuestion.decReviewCnt();
		questionQueryRepository.save(parentQuestion);
	}
}
