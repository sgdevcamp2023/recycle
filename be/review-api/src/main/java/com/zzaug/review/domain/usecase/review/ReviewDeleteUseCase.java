package com.zzaug.review.domain.usecase.review;

import com.zzaug.review.domain.dto.review.ReviewDeleteUseCaseRequest;
import com.zzaug.review.domain.event.review.DeleteReviewEvent;
import com.zzaug.review.domain.event.review.ReviewCntEvent;
import com.zzaug.review.domain.persistence.question.QuestionRepository;
import com.zzaug.review.domain.persistence.review.ReviewRepository;
import com.zzaug.review.domain.usecase.review.event.converter.DeleteReviewEventConverter;
import com.zzaug.review.domain.usecase.review.event.converter.ReviewCntEventConverter;
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
public class ReviewDeleteUseCase {
	private final ReviewRepository reviewRepository;
	private final QuestionRepository questionRepository;
	private final ApplicationEventPublisher publisher;

	@Transactional
	public void execute(ReviewDeleteUseCaseRequest request) {
		ReviewEntity review =
				reviewRepository
						.findById(request.getReviewId())
						.orElseThrow(() -> new NoSuchElementException("요청에 대한 응답을 찾을 수 없습니다."));

		if (!review.getAuthorId().equals(request.getAuthorId())) {
			throw new RuntimeException("접근 권한이 없습니다.");
		}

		reviewRepository.deleteById(request.getReviewId());

		QuestionEntity resultDec = decReviewCount(request.getQuestionId());

		// 리뷰 삭제 이벤트 발행 ( effect : 리뷰 삭제 및 리뷰 카운트 감소 )

		publishEvent(DeleteReviewEventConverter.from(review));

		publishEvent(ReviewCntEventConverter.from(resultDec));
	}

	private QuestionEntity decReviewCount(Long questionId) {
		QuestionEntity target =
				questionRepository
						.findById(questionId)
						.orElseThrow(() -> new NoSuchElementException("요청에 대한 응답을 찾을 수 없습니다."));
		target.decReviewCnt();
		return target;
	}

	private void publishEvent(DeleteReviewEvent event) {
		publisher.publishEvent(event);
	}

	private void publishEvent(ReviewCntEvent event) {
		publisher.publishEvent(event);
	}
}
