package com.zzaug.review.domain.usecase.review.query;

import com.zzaug.review.domain.dto.review.query.ReviewQueryCreateUseCaseRequest;
import com.zzaug.review.domain.event.SaveReviewEvent;
import com.zzaug.review.domain.model.review.query.ReviewQuery;
import com.zzaug.review.domain.persistence.question.QuestionQueryRepository;
import com.zzaug.review.domain.persistence.review.ReviewQueryRepository;
import com.zzaug.review.domain.support.entity.ReviewQueryEntityConverter;
import javax.transaction.Transactional;

import com.zzaug.review.entity.question.query.QuestionQueryEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewQueryCreateUseCase {

	private final ReviewQueryRepository reviewQueryRepository;
	private final ReviewQueryConverter reviewQueryConverter;
	private final QuestionQueryRepository questionQueryRepository;

	@Transactional
	@EventListener
	public void execute(SaveReviewEvent event) {
		QuestionQueryEntity parentQuestion = questionQueryRepository.findById(event.getQuestionId())
				.orElseThrow(() -> new NoSuchElementException("Question not found"));
		ReviewQuery review = reviewQueryConverter.from(event);
		reviewQueryRepository.save(ReviewQueryEntityConverter.from(review));

		parentQuestion.incReviewCnt();
		questionQueryRepository.save(parentQuestion);
	}
}