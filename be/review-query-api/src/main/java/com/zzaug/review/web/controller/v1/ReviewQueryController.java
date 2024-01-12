package com.zzaug.review.web.controller.v1;

import com.zzaug.review.domain.dto.review.query.ReviewQueryResponse;
import com.zzaug.review.domain.dto.review.query.ReviewQueryUseCaseRequest;
import com.zzaug.review.domain.dto.review.query.ReviewTempQueryResponse;
import com.zzaug.review.domain.model.review.ReviewType;
import com.zzaug.review.domain.usecase.review.query.ReviewQueryUseCase;
import com.zzaug.review.support.ApiResponse;
import com.zzaug.review.support.ApiResponseGenerator;
import com.zzaug.review.web.dto.review.query.ReviewQueryRequest;
import com.zzaug.review.web.support.usecase.ReviewUseCaseRequestConverter;
import com.zzaug.security.authentication.token.TokenUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/question-query")
@RequiredArgsConstructor
public class ReviewQueryController {

	private final ReviewQueryUseCase reviewUseCase;

//	@GetMapping()
//	public ApiResponse<ApiResponse.SuccessBody<ReviewQueryResponse>> foo(
//			@AuthenticationPrincipal TokenUserDetails userDetails, ReviewQueryRequest request) {
//		ReviewQueryUseCaseRequest useCaseRequest = ReviewUseCaseRequestConverter.from(request);
//		//		ReviewResponse res= ReviewResponse.builder().name("name").build();
//		ReviewQueryResponse res = ReviewQueryResponse.builder().name("name").build();
//		return ApiResponseGenerator.success(res, HttpStatus.OK);
//	}

	@GetMapping("/questions/{question_id}/reviews")
	public ApiResponse<ApiResponse.SuccessBody<List<ReviewQueryResponse>>> viewQuestionReviewList(
			@AuthenticationPrincipal TokenUserDetails userDetails, @PathVariable Long question_id) {

		List<ReviewQueryResponse> responses = new ArrayList<>();
		ReviewQueryResponse res =
				ReviewQueryResponse.builder()
						.review_id(1L)
						.question_id(1L)
						.content("content")
						.location("location")
						.author("author")
						.author_id(1L)
						.created_at(new Timestamp(System.currentTimeMillis()))
						.updated_at(new Timestamp(System.currentTimeMillis()))
						.tag(ReviewType.CODE)
						.build();
		responses.add(res);
		return ApiResponseGenerator.success(responses, HttpStatus.OK);
	}

	@GetMapping("/questions/{question_id}/reviews/temp")
	public ApiResponse<ApiResponse.SuccessBody<List<ReviewTempQueryResponse>>> viewTempReviewList(
			@AuthenticationPrincipal TokenUserDetails userDetails,
			@PathVariable Long question_id,
			@RequestParam String t_id) {

		List<ReviewTempQueryResponse> responses = new ArrayList<>();
		ReviewTempQueryResponse res =
				ReviewTempQueryResponse.builder()
						.t_id("UUID")
						.question_id(1L)
						.content("content")
						.location("location")
						.author("author")
						.author_id(1L)
						.created_at(new Timestamp(System.currentTimeMillis()))
						.updated_at(new Timestamp(System.currentTimeMillis()))
						.tag(ReviewType.CODE)
						.build();
		responses.add(res);
		return ApiResponseGenerator.success(responses, HttpStatus.OK);
	}
}
