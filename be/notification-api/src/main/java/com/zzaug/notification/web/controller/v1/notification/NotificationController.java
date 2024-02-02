package com.zzaug.notification.web.controller.v1.notification;

import com.zzaug.notification.domain.dto.notification.BrowseReviewsUseCase;
import com.zzaug.notification.domain.dto.notification.NotificationResponse;
import com.zzaug.notification.domain.dto.notification.NotificationResponses;
import com.zzaug.notification.domain.dto.notification.RequestReviewUseCaseRequest;
import com.zzaug.notification.support.ApiResponse;
import com.zzaug.notification.support.ApiResponseGenerator;
import com.zzaug.notification.support.MessageCode;
import com.zzaug.notification.web.dto.notification.RequestReviewRequest;
import com.zzaug.security.authentication.token.TokenUserDetails;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificationController {

	@PostMapping("/request/reviews")
	public ApiResponse<ApiResponse.Success> requestReview(
			@AuthenticationPrincipal TokenUserDetails userDetails,
			@RequestBody RequestReviewRequest request) {
		//		Long memberId = Long.valueOf(userDetails.getId());
		Long memberId = 1L;
		Long questionId = request.getQuestionId();
		Long requestMemberId = request.getRequestMemberId();
		RequestReviewUseCaseRequest useCaseRequest =
				RequestReviewUseCaseRequest.builder()
						.memberId(memberId)
						.questionId(questionId)
						.requestMemberId(requestMemberId)
						.build();
		return ApiResponseGenerator.success(HttpStatus.OK, MessageCode.SUCCESS);
	}

	@GetMapping()
	public ApiResponse<ApiResponse.SuccessBody<NotificationResponses>> browse(
			@AuthenticationPrincipal TokenUserDetails userDetails) {
		//		Long memberId = Long.valueOf(userDetails.getId());
		Long memberId = 1L;
		BrowseReviewsUseCase useCaseRequest = BrowseReviewsUseCase.builder().memberId(memberId).build();
		NotificationResponse info =
				NotificationResponse.builder()
						.type("type")
						.title("title")
						.content("content")
						.noticeAt(LocalDateTime.now())
						.build();
		NotificationResponses res = new NotificationResponses(List.of(info));
		return ApiResponseGenerator.success(res, HttpStatus.OK);
	}
}
