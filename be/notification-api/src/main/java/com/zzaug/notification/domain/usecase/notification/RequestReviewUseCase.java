package com.zzaug.notification.domain.usecase.notification;

import com.zzaug.notification.domain.dto.notification.RequestReviewUseCaseRequest;
import com.zzaug.notification.domain.event.RequestReviewEvent;
import com.zzaug.notification.domain.external.dao.RequestReviewHistoryDao;
import com.zzaug.notification.entity.notification.RequestReviewHistoryEntity;
import com.zzaug.notification.persistence.support.transaction.UseCaseTransactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RequestReviewUseCase {

	private final RequestReviewHistoryDao requestReviewHistoryDao;
	private final ApplicationEventPublisher applicationEventPublisher;

	@UseCaseTransactional
	public void execute(RequestReviewUseCaseRequest request) {
		final Long memberId = request.getMemberId();
		final Long questionId = request.getQuestionId();
		final Long requestMemberId = request.getRequestMemberId();

		// todo bring memberInfo and check memberId is valid
		// todo bring question info and check question owner is same with memberId
		// todo bring memberInfo and check requestMemberId is valid
		requestReviewHistoryDao.saveRequestReviewHistory(
				RequestReviewHistoryEntity.builder()
						.memberId(memberId)
						.questionId(questionId)
						.requestMemberId(requestMemberId)
						.build());
		publishEvent(questionId, memberId, requestMemberId);
	}

	private void publishEvent(Long questionId, Long memberId, Long requestMemberId) {
		applicationEventPublisher.publishEvent(
				RequestReviewEvent.builder()
						.questionId(questionId)
						.questionOwner(memberId)
						.requestMemberId(requestMemberId)
						.build());
	}
}
