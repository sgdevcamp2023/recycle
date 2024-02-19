package com.zzaug.review.domain.usecase.review;

import com.zzaug.review.config.JpaDataSourceConfig;
import com.zzaug.review.domain.dto.review.ReviewCreateUseCaseRequest;
import com.zzaug.review.domain.event.review.SaveReviewEvent;
import com.zzaug.review.domain.model.member.ReviewerList;
import com.zzaug.review.domain.model.review.Review;
import com.zzaug.review.domain.persistence.member.ReviewerListRepository;
import com.zzaug.review.domain.persistence.question.QuestionRepository;
import com.zzaug.review.domain.persistence.review.ReviewRepository;
import com.zzaug.review.domain.support.entity.ReviewEntityConverter;
import com.zzaug.review.domain.support.entity.ReviewerListEntityConverter;
import com.zzaug.review.domain.usecase.review.converter.ReviewConverter;
import com.zzaug.review.domain.usecase.review.event.converter.SaveReviewEventConverter;
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
public class ReviewCreateUseCase {

	private final ReviewRepository reviewRepository;
	private final ReviewConverter reviewConverter;
	private final QuestionRepository questionRepository;
	private final ReviewerListRepository reviewerListRepository;
	private final ApplicationEventPublisher publisher;

	@Transactional(JpaDataSourceConfig.TRANSACTION_MANAGER_NAME)
	public void execute(ReviewCreateUseCaseRequest request) {
		Review review = reviewConverter.from(request);
		ReviewEntity result = reviewRepository.save(ReviewEntityConverter.from(review));
		QuestionEntity resultInc = incReviewCount(request.getQuestionId());

		//리뷰어 목록에 해당 사용자가 있는지 확인
		if (!reviewerListRepository.existsByReviewerIdAndQuestionId(request.getAuthorId(), request.getQuestionId())) {
			// 리뷰어 목록에 사용자가 없으면 리뷰어 목록에 추가
			ReviewerList writeMember = ReviewerList.builder()
					.reviewerId(request.getAuthorId())
					.reviewerName(request.getAuthor())
					.questionId(request.getQuestionId())
					.build();
			reviewerListRepository.save(ReviewerListEntityConverter.from(writeMember));
		}

		// 리뷰 생성 이벤트 발행 ( effect : 리뷰 생성 및 리뷰 카운트 증가 )
		publishEvent(SaveReviewEventConverter.from(result));
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

}
