package com.zzaug.review.web.controller.v1;

import com.zzaug.review.domain.dto.question.query.QuestionQueryResponse;
import com.zzaug.review.domain.dto.question.query.QuestionTempQueryResponse;
import com.zzaug.review.domain.dto.review.query.ReviewQueryResponse;
import com.zzaug.review.domain.model.review.ReviewType;
import com.zzaug.review.support.ApiResponse;
import com.zzaug.review.support.ApiResponseGenerator;
import com.zzaug.security.authentication.token.TokenUserDetails;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/me-query")
@RequiredArgsConstructor
public class MeQueryController {

	@GetMapping("/reviews")
	public ApiResponse<ApiResponse.SuccessBody<List<ReviewQueryResponse>>> viewMemberReviewList(
			@AuthenticationPrincipal TokenUserDetails userDetails) {

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

	@GetMapping("/questions")
	public ApiResponse<ApiResponse.SuccessBody<List<QuestionQueryResponse>>> viewQuestionList(
			@AuthenticationPrincipal TokenUserDetails userDetails) {

		List<QuestionQueryResponse> res = new ArrayList<>();
		QuestionQueryResponse element =
				QuestionQueryResponse.builder()
						.question_id(1L)
						.content("content")
						.author("author")
						.author_id(1L)
						.review_cnt(1)
						.created_at(new Timestamp(System.currentTimeMillis()))
						.updated_at(new Timestamp(System.currentTimeMillis()))
						.build();
		res.add(element);
		return ApiResponseGenerator.success(res, HttpStatus.OK);
	}

	@GetMapping("/questions/temp")
	public ApiResponse<ApiResponse.SuccessBody<List<QuestionTempQueryResponse>>> viewTempQuestionList(
			@AuthenticationPrincipal TokenUserDetails userDetails) {

		List<QuestionTempQueryResponse> res = new ArrayList<>();
		QuestionTempQueryResponse element =
				QuestionTempQueryResponse.builder()
						.t_id("UUID")
						.content("content")
						.author("author")
						.author_id(1L)
						.created_at(new Timestamp(System.currentTimeMillis()))
						.build();
		res.add(element);
		return ApiResponseGenerator.success(res, HttpStatus.OK);
	}

	@GetMapping("/requests/reviews")
	public ApiResponse<ApiResponse.SuccessBody<List<QuestionQueryResponse>>> viewReviewRequestList(
			@AuthenticationPrincipal TokenUserDetails userDetails) {

		List<QuestionQueryResponse> res = new ArrayList<>();
		QuestionQueryResponse element =
				QuestionQueryResponse.builder()
						.question_id(1L)
						.content("content")
						.author("author")
						.author_id(1L)
						.review_cnt(1)
						.created_at(new Timestamp(System.currentTimeMillis()))
						.updated_at(new Timestamp(System.currentTimeMillis()))
						.build();
		res.add(element);
		return ApiResponseGenerator.success(res, HttpStatus.OK);
	}
}
