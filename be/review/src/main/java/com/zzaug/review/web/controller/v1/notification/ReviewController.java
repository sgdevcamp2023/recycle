package com.zzaug.review.web.controller.v1.notification;

import com.zzaug.review.domain.dto.review.ReviewResponse;
import com.zzaug.review.domain.dto.review.ReviewUseCaseRequest;
import com.zzaug.review.domain.usecase.review.ReviewUseCase;
import com.zzaug.review.support.ApiResponse;
import com.zzaug.review.support.ApiResponseGenerator;
import com.zzaug.review.web.dto.notification.ReviewRequest;
import com.zzaug.review.web.support.usecase.ReviewUseCaseRequestConverter;
import com.zzaug.security.authentication.token.TokenUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/review")
@RequiredArgsConstructor
public class ReviewController {

	private final ReviewUseCase reviewUseCase;

	@PostMapping()
	public ApiResponse<ApiResponse.SuccessBody<ReviewResponse>> foo(
		@AuthenticationPrincipal TokenUserDetails userDetails, ReviewRequest request) {
		ReviewUseCaseRequest useCaseRequest = ReviewUseCaseRequestConverter.from(request);
		//		ReviewResponse res= ReviewResponse.builder().name("name").build();
		ReviewResponse res = ReviewResponse.builder().name("name").build();
		return ApiResponseGenerator.success(res, HttpStatus.OK);
	}
}
