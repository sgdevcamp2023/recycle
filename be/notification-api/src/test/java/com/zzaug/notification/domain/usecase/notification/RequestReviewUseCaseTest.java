package com.zzaug.notification.domain.usecase.notification;

import com.zzaug.notification.NotificationApp;
import com.zzaug.notification.domain.dto.notification.RequestReviewUseCaseRequest;
import com.zzaug.notification.domain.usecase.AbstractUseCaseTest;
import com.zzaug.notification.domain.usecase.config.mock.UMockRequestReviewHistoryDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {NotificationApp.class, UMockRequestReviewHistoryDao.class})
class RequestReviewUseCaseTest extends AbstractUseCaseTest {

	@Autowired private RequestReviewUseCase requestReviewUseCase;

	@Test
	void 리뷰요청_테스트() {
		// Given
		RequestReviewUseCaseRequest useCaseRequest =
				RequestReviewUseCaseRequest.builder()
						.memberId(UMockRequestReviewHistoryDao.MEMBER_ID)
						.questionId(UMockRequestReviewHistoryDao.QUESTION_ID)
						.requestMemberId(UMockRequestReviewHistoryDao.REQUEST_MEMBER_ID)
						.build();

		// When
		requestReviewUseCase.execute(useCaseRequest);
	}
}
