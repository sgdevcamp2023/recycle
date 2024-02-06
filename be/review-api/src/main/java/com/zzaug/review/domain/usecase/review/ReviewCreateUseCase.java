package com.zzaug.review.domain.usecase.review;

import com.zzaug.review.domain.dto.review.ReviewCreateUseCaseRequest;
import com.zzaug.review.domain.event.review.ReviewCntEvent;
import com.zzaug.review.domain.event.review.SaveReviewEvent;
import com.zzaug.review.domain.model.review.Review;
import com.zzaug.review.domain.persistence.question.QuestionRepository;
import com.zzaug.review.domain.persistence.review.ReviewRepository;
import com.zzaug.review.domain.support.entity.ReviewEntityConverter;
import com.zzaug.review.domain.usecase.review.converter.ReviewConverter;
import com.zzaug.review.domain.usecase.review.event.converter.ReviewCntEventConverter;
import com.zzaug.review.domain.usecase.review.event.converter.SaveReviewEventConverter;
import com.zzaug.review.entity.question.QuestionEntity;
import com.zzaug.review.entity.review.ReviewEntity;
import java.util.NoSuchElementException;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewCreateUseCase {

	private final ReviewRepository reviewRepository;
	private final ReviewConverter reviewConverter;
	private final QuestionRepository questionRepository;
	private final ApplicationEventPublisher publisher;

	@Transactional
	public void execute(ReviewCreateUseCaseRequest request) {
		Review review = reviewConverter.from(request);
		ReviewEntity result = reviewRepository.save(ReviewEntityConverter.from(review));
		QuestionEntity resultInc = incReviewCount(request.getQuestionId());
		// 리뷰 생성 이벤트 발행 ( effect : 리뷰 생성 및 리뷰 카운트 증가 )
		publishEvent(SaveReviewEventConverter.from(result));
		publishEvent(ReviewCntEventConverter.from(resultInc));
	}

	private QuestionEntity incReviewCount(Long questionId) {
		QuestionEntity target =
				questionRepository
						.findById(questionId)
						.orElseThrow(() -> new NoSuchElementException("요청에 대한 응답을 찾을 수 없습니다."));
		target.incReviewCnt();
		return target;
	}

	private void publishEvent(SaveReviewEvent event) {
		publisher.publishEvent(event);
	}

	private void publishEvent(ReviewCntEvent event) {
		publisher.publishEvent(event);
	}
}
