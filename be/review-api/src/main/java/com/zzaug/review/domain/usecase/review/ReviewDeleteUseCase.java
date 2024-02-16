package com.zzaug.review.domain.usecase.review;

import com.zzaug.review.config.JpaDataSourceConfig;
import com.zzaug.review.domain.dto.review.ReviewDeleteUseCaseRequest;
import com.zzaug.review.domain.exception.AlreadyDeletedException;
import com.zzaug.review.domain.exception.UnAuthorizationException;
import com.zzaug.review.domain.event.review.DeleteReviewEvent;
import com.zzaug.review.domain.persistence.member.ReviewerListRepository;
import com.zzaug.review.domain.persistence.question.QuestionRepository;
import com.zzaug.review.domain.persistence.review.ReviewRepository;
import com.zzaug.review.domain.usecase.review.event.converter.DeleteReviewEventConverter;
import com.zzaug.review.entity.question.QuestionEntity;
import com.zzaug.review.entity.review.ReviewEntity;
import java.util.NoSuchElementException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewDeleteUseCase {
	private final ReviewRepository reviewRepository;
	private final QuestionRepository questionRepository;
	private final ApplicationEventPublisher publisher;
	private final ReviewerListRepository reviewerListRepository;

	@Transactional(JpaDataSourceConfig.TRANSACTION_MANAGER_NAME)
	public void execute(ReviewDeleteUseCaseRequest request) {
		ReviewEntity review =
				reviewRepository
						.findById(request.getReviewId())
						.orElseThrow(() -> new NoSuchElementException("요청에 대한 응답을 찾을 수 없습니다."));

		if (!review.getAuthorId().equals(request.getAuthorId())) {
			throw new UnAuthorizationException("접근 권한이 없습니다.");
		}

		if (review.isDeleted()) {
			throw new AlreadyDeletedException("이미 삭제된 리뷰입니다.");
		}

		review.deleteReview();

		QuestionEntity resultDec = decReviewCount(request.getQuestionId());

		// 리뷰 목록에 해당 사용자의 다른 리뷰가 있는지 확인
		if (!reviewRepository.existsByAuthorIdAndQuestionIdAndIsDeletedFalse(request.getAuthorId(), request.getQuestionId())) {
			// 리뷰 목록에 사용자의 다른 리뷰가 없으면 리뷰어 목록에서 삭제
			reviewerListRepository.deleteByReviewerIdAndQuestionId(request.getAuthorId(), request.getQuestionId());
		}

		// 리뷰 삭제 이벤트 발행 ( effect : 리뷰 삭제 및 리뷰 카운트 감소 )
		publishEvent(DeleteReviewEventConverter.from(review));

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

}
