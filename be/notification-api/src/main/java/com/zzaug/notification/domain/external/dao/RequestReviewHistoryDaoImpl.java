package com.zzaug.notification.domain.external.dao;

import com.zzaug.notification.entity.notification.RequestReviewHistoryEntity;
import com.zzaug.notification.persistence.notification.RequestReviewHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Slf4j
@Profile("!usecase-test")
@Repository
@RequiredArgsConstructor
public class RequestReviewHistoryDaoImpl implements RequestReviewHistoryDao {

	private final RequestReviewHistoryRepository requestReviewHistoryRepository;

	@Override
	public RequestReviewHistoryEntity saveRequestReviewHistory(
			RequestReviewHistoryEntity requestReviewHistoryEntity) {
		return requestReviewHistoryRepository.save(requestReviewHistoryEntity);
	}
}
