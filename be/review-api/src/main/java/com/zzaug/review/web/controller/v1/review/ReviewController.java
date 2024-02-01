package com.zzaug.review.web.controller.v1.review;

import com.zzaug.review.domain.dto.review.*;
import com.zzaug.review.domain.usecase.review.*;
import com.zzaug.review.support.ApiResponse;
import com.zzaug.review.support.ApiResponseGenerator;
import com.zzaug.review.support.MessageCode;
import com.zzaug.review.web.dto.review.ReviewDeleteRequest;
import com.zzaug.review.web.dto.review.ReviewRequest;
import com.zzaug.review.web.dto.review.ReviewTempRequest;
import com.zzaug.review.web.support.usecase.*;
import com.zzaug.security.authentication.token.TokenUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ReviewController {

	private final ReviewCreateUseCase reviewCreateUseCase;
	private final ReviewTempCreateUseCase reviewTempCreateUseCase;
	private final ReviewEditUseCase reviewEditUseCase;
	private final ReviewDeleteUseCase reviewDeleteUseCase;
	private final ReviewTempViewUseCase reviewTempViewUseCase;

	@PostMapping("/questions/{questionId}/reviews")
	public ApiResponse<ApiResponse.SuccessBody<Void>> createReview(
			@AuthenticationPrincipal TokenUserDetails userDetails,
			@PathVariable Long questionId,
			@RequestBody ReviewRequest request) {

		ReviewCreateUseCaseRequest useCaseRequest =
				ReviewCreateUseCaseRequestConverter.from(request, questionId, userDetails);
		reviewCreateUseCase.execute(useCaseRequest);

		return ApiResponseGenerator.success(HttpStatus.OK, MessageCode.RESOURCE_CREATED);
	}


	@PostMapping("/questions/{questionId}/reviews/temp")
	public ApiResponse<ApiResponse.SuccessBody<Void>> createTempReview(
			@AuthenticationPrincipal TokenUserDetails userDetails,
			@PathVariable Long questionId,
			@RequestBody ReviewTempRequest request) {

		ReviewTempCreateUseCaseRequest useCaseRequest =
				ReviewTempUseCaseRequestConverter.from(request, questionId, userDetails);
		reviewTempCreateUseCase.execute(useCaseRequest);

		return ApiResponseGenerator.success(HttpStatus.OK, MessageCode.RESOURCE_CREATED);
	}
	@PutMapping("/questions/{questionId}/reviews/{reviewId}")
	public ApiResponse<ApiResponse.SuccessBody<Void>> editReview(
			@AuthenticationPrincipal TokenUserDetails userDetails,
			@PathVariable Long questionId,
			@PathVariable Long reviewId,
			@RequestBody ReviewRequest request) {

		ReviewEditUseCaseRequest useCaseRequest =
				ReviewEditUseCaseRequestConverter.from(request, reviewId, questionId, userDetails);
		try {
			reviewEditUseCase.execute(useCaseRequest);
			return ApiResponseGenerator.success(HttpStatus.OK, MessageCode.RESOURCE_MODIFIED);
		}catch (NoSuchElementException e){
			return ApiResponseGenerator.success(HttpStatus.OK, MessageCode.RESOURCE_MODIFIED);
		}catch (RuntimeException e){
			return ApiResponseGenerator.success(HttpStatus.OK, MessageCode.RESOURCE_MODIFIED);
		}

	}
	@DeleteMapping("/questions/{questionId}/reviews/{reviewId}")
	public ApiResponse<ApiResponse.SuccessBody<Void>> deleteReview(
			@AuthenticationPrincipal TokenUserDetails userDetails,
			@PathVariable Long questionId,
			@PathVariable Long reviewId) {

		ReviewDeleteRequest request = ReviewDeleteRequest.builder()
				.reviewId(reviewId)
				.questionId(questionId)
				.authorId(Long.valueOf(userDetails.getId()))
				.build();

		ReviewDeleteUseCaseRequest useCaseRequest =
				ReviewDeleteUseCaseRequestConverter.from(request);

		try {
			reviewDeleteUseCase.execute(useCaseRequest);
			return ApiResponseGenerator.success(HttpStatus.OK, MessageCode.RESOURCE_DELETED);
		}catch (NoSuchElementException e){
			return ApiResponseGenerator.success(HttpStatus.OK, MessageCode.RESOURCE_DELETED);
		}catch (RuntimeException e){
			return ApiResponseGenerator.success(HttpStatus.OK, MessageCode.RESOURCE_DELETED);
		}
	}
	@GetMapping("/questions/{questionId}/reviews/temp")
	public ApiResponse<ApiResponse.SuccessBody<List<ReviewTempResponse>>> viewTempReviewList(
			@AuthenticationPrincipal TokenUserDetails userDetails,
			@PathVariable Long questionId,
			@RequestParam(required = false) String tempId) {

		ReviewTempViewUseCaseRequest useCaseRequest = new ReviewTempViewUseCaseRequest();

		if (tempId == null){
			useCaseRequest =
					ReviewTempViewUseCaseRequestConverter.from(tempId);
		} else {
			useCaseRequest =
					ReviewTempViewUseCaseRequestConverter.from(Long.valueOf(userDetails.getId()), questionId);
		}

		List<ReviewTempResponse> responses = reviewTempViewUseCase.execute(useCaseRequest);

		return ApiResponseGenerator.success(responses, HttpStatus.OK, MessageCode.SUCCESS);
	}
}
