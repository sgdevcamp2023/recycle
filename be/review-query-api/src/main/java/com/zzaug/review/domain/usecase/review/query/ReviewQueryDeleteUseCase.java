package com.zzaug.review.domain.usecase.review.query;

import com.zzaug.review.domain.dto.review.query.ReviewQueryDeleteUseCaseRequest;
import com.zzaug.review.domain.event.DeleteReviewEvent;
import com.zzaug.review.domain.model.review.query.ReviewQuery;
import com.zzaug.review.domain.persistence.question.QuestionQueryRepository;
import com.zzaug.review.domain.persistence.review.ReviewQueryRepository;
import javax.transaction.Transactional;

import com.zzaug.review.domain.support.entity.ReviewQueryEntityConverter;
import com.zzaug.review.entity.question.query.QuestionQueryEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewQueryDeleteUseCase {
	private final ReviewQueryRepository reviewQueryRepository;
	private final QuestionQueryRepository questionQueryRepository;
	private final ReviewQueryConverter reviewQueryConverter;

	@Transactional
	@EventListener
	public void execute(DeleteReviewEvent event) {
		QuestionQueryEntity parentQuestion = questionQueryRepository.findById(event.getQuestionId())
				.orElseThrow(() -> new NoSuchElementException("Question not found"));
		ReviewQuery review = reviewQueryConverter.from(event);
		reviewQueryRepository.save(ReviewQueryEntityConverter.from(review));

		parentQuestion.decReviewCnt();
		questionQueryRepository.save(parentQuestion);
	}
}
