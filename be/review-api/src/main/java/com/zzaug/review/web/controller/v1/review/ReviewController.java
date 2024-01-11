package com.zzaug.review.web.controller.v1.review;

import com.zzaug.review.domain.dto.review.ReviewResponse;
import com.zzaug.review.domain.dto.review.ReviewTempResponse;
import com.zzaug.review.domain.usecase.review.ReviewUseCase;
import com.zzaug.review.support.ApiResponse;
import com.zzaug.review.support.ApiResponseGenerator;
import com.zzaug.review.web.dto.review.ReviewRequest;
import com.zzaug.review.web.dto.review.ReviewTempRequest;
import com.zzaug.security.authentication.token.TokenUserDetails;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.zzaug.security.exception.AccessTokenInvalidException;
import com.zzaug.security.token.TokenResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ReviewController {

	private final ReviewUseCase reviewUseCase;

	//	@PostMapping()
	//	public ApiResponse<ApiResponse.SuccessBody<ReviewResponse>> foo(
	//			@AuthenticationPrincipal TokenUserDetails userDetails, ReviewRequest request) {
	//		ReviewUseCaseRequest useCaseRequest = ReviewUseCaseRequestConverter.from(request);
	//		//		ReviewResponse res= ReviewResponse.builder().name("name").build();
	//		ReviewResponse res = ReviewResponse.builder().name("name").build();
	//		return ApiResponseGenerator.success(res, HttpStatus.OK);
	//	}
	@PostMapping("/questions/{question_id}/reviews")
	public ApiResponse<ApiResponse.SuccessBody<Void>> createReview(
			@AuthenticationPrincipal TokenUserDetails userDetails,
			@RequestParam Long question_id,
			ReviewRequest request) {

		return ApiResponseGenerator.success(HttpStatus.OK);
	}

	@PostMapping("/questions/{question_id}/reviews/temp")
	public ApiResponse<ApiResponse.SuccessBody<Void>> createTempReview(
			@AuthenticationPrincipal TokenUserDetails userDetails,
			@RequestParam Long question_id,
			ReviewTempRequest request) {

		return ApiResponseGenerator.success(HttpStatus.OK);
	}

	@GetMapping("/me/reviews")
	public ApiResponse<ApiResponse.SuccessBody<List<ReviewResponse>>> viewMemberReviewList(
			@AuthenticationPrincipal TokenUserDetails userDetails) {

		List<ReviewResponse> responses = new ArrayList<>();
		ReviewResponse res =
				ReviewResponse.builder()
						.review_id(1L)
						.question_id(1L)
						.content("content")
						.author("author")
						.author_id(1L)
						.created_at(new Timestamp(System.currentTimeMillis()))
						.updated_at(new Timestamp(System.currentTimeMillis()))
						.tag("tag")
						.build();
		responses.add(res);
		return ApiResponseGenerator.success(responses, HttpStatus.OK);
	}

	@GetMapping("/questions/{question_id}/reviews")
	public ApiResponse<ApiResponse.SuccessBody<List<ReviewResponse>>> viewQuestionReviewList(
			@AuthenticationPrincipal TokenUserDetails userDetails, @RequestParam Long question_id) {

		List<ReviewResponse> responses = new ArrayList<>();
		ReviewResponse res =
				ReviewResponse.builder()
						.review_id(1L)
						.question_id(1L)
						.content("content")
						.author("author")
						.author_id(1L)
						.created_at(new Timestamp(System.currentTimeMillis()))
						.updated_at(new Timestamp(System.currentTimeMillis()))
						.tag("tag")
						.build();
		responses.add(res);
		return ApiResponseGenerator.success(responses, HttpStatus.OK);
	}

	@GetMapping("/questions/{question_id}/reviews/temp")
	public ApiResponse<ApiResponse.SuccessBody<List<ReviewTempResponse>>> viewTempReviewList(
			@AuthenticationPrincipal TokenUserDetails userDetails,
			@PathVariable Long question_id,
			@RequestParam String t_id) {

		List<ReviewTempResponse> responses = new ArrayList<>();
		ReviewTempResponse res =
				ReviewTempResponse.builder()
						.t_id("UUID")
						.question_id(1L)
						.content("content")
						.author("author")
						.author_id(1L)
						.created_at(new Timestamp(System.currentTimeMillis()))
						.updated_at(new Timestamp(System.currentTimeMillis()))
						.tag("tag")
						.build();
		responses.add(res);
		return ApiResponseGenerator.success(responses, HttpStatus.OK);
	}

	@PutMapping("/questions/{question_id}/reviews/{review_id}")
	public ApiResponse<ApiResponse.SuccessBody<Void>> editReview(
			@AuthenticationPrincipal TokenUserDetails userDetails,
			@PathVariable Long question_id,
			@PathVariable Long review_id) {

		return ApiResponseGenerator.success(HttpStatus.OK);
	}

	@DeleteMapping("/questions/{question_id}/reviews/{review_id}")
	public ApiResponse<ApiResponse.SuccessBody<Void>> deleteReview(
			@AuthenticationPrincipal TokenUserDetails userDetails,
			@PathVariable Long question_id,
			@PathVariable Long review_id) {

		return ApiResponseGenerator.success(HttpStatus.OK);
	}
}
