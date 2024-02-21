package com.zzaug.review.domain.usecase.review.query;

import com.zzaug.review.config.JpaDataSourceConfig;
import com.zzaug.review.domain.event.review.SaveReviewEvent;
import com.zzaug.review.domain.model.review.query.ReviewQuery;
import com.zzaug.review.domain.persistence.question.QuestionQueryRepository;
import com.zzaug.review.domain.persistence.review.ReviewQueryRepository;
import com.zzaug.review.domain.support.entity.ReviewQueryEntityConverter;
import com.zzaug.review.entity.question.query.QuestionQueryEntity;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewQueryCreateUseCase {

	private final ReviewQueryRepository reviewQueryRepository;
	private final ReviewQueryConverter reviewQueryConverter;
	private final QuestionQueryRepository questionQueryRepository;

	@Transactional(JpaDataSourceConfig.TRANSACTION_MANAGER_NAME)
	@EventListener
	public void execute(SaveReviewEvent event) {
		QuestionQueryEntity parentQuestion =
				questionQueryRepository
						.findById(event.getQuestionId())
						.orElseThrow(() -> new NoSuchElementException("Question not found"));
		ReviewQuery review = reviewQueryConverter.from(event);
		reviewQueryRepository.save(ReviewQueryEntityConverter.from(review));

		parentQuestion.incReviewCnt();
		questionQueryRepository.save(parentQuestion);
	}
}
