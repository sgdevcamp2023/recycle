package com.zzaug.notification.domain.external.dao;

import com.zzaug.notification.entity.notification.RequestReviewHistoryEntity;

public interface RequestReviewHistoryDao {

	RequestReviewHistoryEntity saveRequestReviewHistory(
			RequestReviewHistoryEntity requestReviewHistoryEntity);
}
