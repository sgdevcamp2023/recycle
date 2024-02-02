package com.zzaug.review.web.controller.v1.review;

import com.zzaug.review.domain.usecase.review.ReviewUseCase;
import com.zzaug.review.support.ApiResponse;
import com.zzaug.review.support.ApiResponseGenerator;
import com.zzaug.review.support.MessageCode;
import com.zzaug.review.web.dto.review.ReviewRequest;
import com.zzaug.review.web.dto.review.ReviewTempRequest;
import com.zzaug.security.authentication.token.TokenUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ReviewController {

	private final ReviewUseCase reviewUseCase;

	@PostMapping("/questions/{question_id}/reviews")
	public ApiResponse<ApiResponse.SuccessBody<Void>> createReview(
			@AuthenticationPrincipal TokenUserDetails userDetails,
			@PathVariable Long question_id,
			@RequestBody ReviewRequest request) {
		return ApiResponseGenerator.success(HttpStatus.OK, MessageCode.RESOURCE_CREATED);
	}

	@PostMapping("/questions/{question_id}/reviews/temp")
	public ApiResponse<ApiResponse.SuccessBody<Void>> createTempReview(
			@AuthenticationPrincipal TokenUserDetails userDetails,
			@PathVariable Long question_id,
			@RequestBody ReviewTempRequest request) {

		return ApiResponseGenerator.success(HttpStatus.OK, MessageCode.RESOURCE_CREATED);
	}
	@PutMapping("/questions/{question_id}/reviews/{review_id}")
	public ApiResponse<ApiResponse.SuccessBody<Void>> editReview(
			@AuthenticationPrincipal TokenUserDetails userDetails,
			@PathVariable Long question_id,
			@PathVariable Long review_id,
			@RequestBody ReviewRequest request) {

		return ApiResponseGenerator.success(HttpStatus.OK, MessageCode.RESOURCE_MODIFIED);
	}

	@DeleteMapping("/questions/{question_id}/reviews/{review_id}")
	public ApiResponse<ApiResponse.SuccessBody<Void>> deleteReview(
			@AuthenticationPrincipal TokenUserDetails userDetails,
			@PathVariable Long question_id,
			@PathVariable Long review_id) {

		return ApiResponseGenerator.success(HttpStatus.OK, MessageCode.RESOURCE_DELETED);
	}
}
