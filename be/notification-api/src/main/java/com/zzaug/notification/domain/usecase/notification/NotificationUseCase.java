package com.zzaug.notification.domain.usecase.notification;

import com.zzaug.notification.domain.dto.notification.NotificationResponse;
import com.zzaug.notification.domain.dto.notification.NotificationUseCaseRequest;
import com.zzaug.notification.domain.model.notification.Notification;
import com.zzaug.notification.domain.persistence.notification.NotificationRepository;
import com.zzaug.notification.domain.support.entity.NotificationEntityConverter;
import com.zzaug.notification.domain.util.UtilSomething;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationUseCase {

	private final NotificationRepository notificationRepository;
	private final NotificationConverter notificationConverter;

	@Transactional
	public NotificationResponse execute(NotificationUseCaseRequest request) {
		Notification notification = notificationConverter.from(request);

		notificationRepository.save(NotificationEntityConverter.from(notification));

		List<Notification> all =
				notificationRepository.findAll().stream()
						.map(notificationConverter::from)
						.collect(Collectors.toList());

		UtilSomething.a("a");
		UtilSomething.b("b");
		UtilSomething.c("c");

		return NotificationResponse.builder().build();
	}
}
