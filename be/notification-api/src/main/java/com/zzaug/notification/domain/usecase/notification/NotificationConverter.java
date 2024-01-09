package com.zzaug.notification.domain.usecase.notification;

import com.zzaug.notification.domain.dto.notification.NotificationUseCaseRequest;
import com.zzaug.notification.domain.model.notification.Notification;
import com.zzaug.notification.entity.notification.NotificationEntity;
import org.springframework.stereotype.Component;

@Component
public class NotificationConverter {

	public Notification from(NotificationUseCaseRequest source) {
		return Notification.builder().name(source.getName()).build();
	}

	public Notification from(NotificationEntity source) {
		return Notification.builder().name(source.getId().toString()).build();
	}
}
