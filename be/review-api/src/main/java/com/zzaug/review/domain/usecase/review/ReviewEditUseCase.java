package com.zzaug.review.domain.usecase.review;

import com.zzaug.review.domain.dto.review.ReviewEditUseCaseRequest;
import com.zzaug.review.domain.event.review.EditReviewEvent;
import com.zzaug.review.domain.persistence.review.ReviewRepository;
import com.zzaug.review.domain.usecase.review.event.converter.EditReviewEventConverter;
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
public class ReviewEditUseCase {

	private final ReviewRepository reviewRepository;
	private final ApplicationEventPublisher publisher;

	@Transactional
	public void execute(ReviewEditUseCaseRequest request) {
		ReviewEntity review =
				reviewRepository
						.findById(request.getReviewId())
						.orElseThrow(() -> new NoSuchElementException("요청에 대한 응답을 찾을 수 없습니다."));

		if (!review.getAuthorId().equals(request.getAuthorId())) {
			throw new RuntimeException("접근 권한이 없습니다.");
		}

		review.update(
				request.getContent(),
				request.getStartPoint(),
				request.getEndPoint(),
				request.getUpdatedAt());

		publishEvent(EditReviewEventConverter.from(review));
	}

	private void publishEvent(EditReviewEvent event) {
		publisher.publishEvent(event);
	}
}
