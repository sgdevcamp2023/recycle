package com.zzaug.notification.web.support.usecase;

import com.zzaug.notification.domain.dto.notification.NotificationUseCaseRequest;
import com.zzaug.notification.web.dto.notification.NotificationRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class NotificationUseCaseRequestConverter {

	public static NotificationUseCaseRequest from(NotificationRequest request) {
		return NotificationUseCaseRequest.builder().name(request.getName()).build();
	}
}
