package com.zzaug.notification.domain.persistence.notification;

import com.zzaug.notification.entity.notification.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {}
