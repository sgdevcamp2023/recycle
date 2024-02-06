package com.zzaug.review.domain.usecase.review;

import com.zzaug.review.domain.dto.review.ReviewDeleteUseCaseRequest;
import com.zzaug.review.domain.exception.AlreadyDeletedException;
import com.zzaug.review.domain.exception.UnAuthorizationException;
import com.zzaug.review.domain.persistence.question.QuestionRepository;
import com.zzaug.review.domain.persistence.review.ReviewRepository;
import com.zzaug.review.entity.review.ReviewEntity;
import java.util.NoSuchElementException;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewDeleteUseCase {
	private final ReviewRepository reviewRepository;
	private final QuestionRepository questionRepository;

	@Transactional
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

		decReviewCount(request.getQuestionId());

		// 리뷰 삭제 이벤트 발행 ( effect : 리뷰 삭제 및 리뷰 카운트 감소 )

	}

	private void decReviewCount(Long questionId) {
		questionRepository.decReviewCnt(questionId);
	}
}
