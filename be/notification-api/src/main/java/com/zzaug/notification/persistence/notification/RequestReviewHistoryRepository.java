package com.zzaug.notification.persistence.notification;

import com.zzaug.notification.entity.notification.RequestReviewHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestReviewHistoryRepository
		extends JpaRepository<RequestReviewHistoryEntity, Long> {}
