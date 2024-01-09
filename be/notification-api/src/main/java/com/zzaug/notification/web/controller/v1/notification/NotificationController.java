package com.zzaug.notification.web.controller.v1.notification;

import com.zzaug.notification.domain.dto.notification.NotificationResponse;
import com.zzaug.notification.domain.dto.notification.NotificationUseCaseRequest;
import com.zzaug.notification.domain.usecase.notification.NotificationUseCase;
import com.zzaug.notification.support.ApiResponse;
import com.zzaug.notification.support.ApiResponseGenerator;
import com.zzaug.notification.web.dto.notification.NotificationRequest;
import com.zzaug.notification.web.support.usecase.NotificationUseCaseRequestConverter;
import com.zzaug.security.authentication.token.TokenUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificationController {

	private final NotificationUseCase notificationUseCase;

	@PostMapping()
	public ApiResponse<ApiResponse.SuccessBody<NotificationResponse>> notification(
			@AuthenticationPrincipal TokenUserDetails userDetails, NotificationRequest request) {
		NotificationUseCaseRequest useCaseRequest = NotificationUseCaseRequestConverter.from(request);
		//		NotificationResponse res = notificationUseCase.execute(useCaseRequest);
		NotificationResponse res = NotificationResponse.builder().name("name").build();
		return ApiResponseGenerator.success(res, HttpStatus.OK);
	}
}
