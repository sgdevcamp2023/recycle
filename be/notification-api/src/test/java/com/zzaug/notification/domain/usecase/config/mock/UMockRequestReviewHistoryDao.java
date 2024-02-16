package com.zzaug.notification.domain.usecase.config.mock;

import com.zzaug.notification.domain.external.dao.RequestReviewHistoryDao;
import com.zzaug.notification.entity.notification.RequestReviewHistoryEntity;
import org.springframework.boot.test.context.TestComponent;

@TestComponent
public class UMockRequestReviewHistoryDao implements RequestReviewHistoryDao {

	@Override
	public RequestReviewHistoryEntity saveRequestReviewHistory(
			RequestReviewHistoryEntity requestReviewHistoryEntity) {
		return requestReviewHistoryEntity;
	}
}
