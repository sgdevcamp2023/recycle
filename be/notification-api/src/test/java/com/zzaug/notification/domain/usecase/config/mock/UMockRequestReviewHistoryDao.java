package com.zzaug.notification.domain.usecase.config.mock;

import com.zzaug.notification.domain.external.dao.RequestReviewHistoryDao;
import com.zzaug.notification.entity.notification.RequestReviewHistoryEntity;
import org.springframework.boot.test.context.TestComponent;

@TestComponent
public class UMockRequestReviewHistoryDao implements RequestReviewHistoryDao {

	public static final Long MEMBER_ID = 1L;
	public static final Long QUESTION_ID = 1L;
	public static final Long REQUEST_MEMBER_ID = 2L;

	@Override
	public RequestReviewHistoryEntity saveRequestReviewHistory(
			RequestReviewHistoryEntity requestReviewHistoryEntity) {
		return requestReviewHistoryEntity;
	}
}
