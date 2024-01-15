package com.zzaug.review.web.controller.v1;

import com.zzaug.review.domain.dto.review.query.ReviewQueryResponse;
import com.zzaug.review.domain.dto.review.query.ReviewTempQueryResponse;
import com.zzaug.review.domain.model.review.ReviewType;
import com.zzaug.review.domain.usecase.review.query.ReviewQueryUseCase;
import com.zzaug.review.support.ApiResponse;
import com.zzaug.review.support.ApiResponseGenerator;
import com.zzaug.review.support.MessageCode;
import com.zzaug.security.authentication.token.TokenUserDetails;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/question-query")
@RequiredArgsConstructor
public class ReviewQueryController {

	private final ReviewQueryUseCase reviewUseCase;

	@GetMapping("/{question_id}/reviews")
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
		return ApiResponseGenerator.success(responses, HttpStatus.OK, MessageCode.SUCCESS);
	}

	@GetMapping("/{question_id}/reviews/temp")
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
		return ApiResponseGenerator.success(responses, HttpStatus.OK, MessageCode.SUCCESS);
	}
}
