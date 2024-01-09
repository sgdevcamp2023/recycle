package com.zzaug.notification.domain.support.entity;

import com.zzaug.notification.domain.model.notification.Notification;
import com.zzaug.notification.entity.notification.NotificationEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class NotificationEntityConverter {

	public static NotificationEntity from(Notification source) {
		return NotificationEntity.builder().build();
	}
}
