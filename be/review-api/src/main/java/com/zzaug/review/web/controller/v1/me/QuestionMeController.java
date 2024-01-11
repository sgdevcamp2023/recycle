package com.zzaug.review.web.controller.v1.me;

import com.zzaug.review.domain.dto.member.MemberResponse;
import com.zzaug.review.domain.dto.question.QuestionResponse;
import com.zzaug.review.domain.dto.question.QuestionTempResponse;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/me")
@RequiredArgsConstructor
public class QuestionMeController {

	@GetMapping("/questions")
	public ApiResponse<ApiResponse.SuccessBody<List<QuestionResponse>>> viewQuestionList(
			@AuthenticationPrincipal TokenUserDetails userDetails) {

		List<QuestionResponse> res = new ArrayList<>();
		QuestionResponse element =
				QuestionResponse.builder()
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
	public ApiResponse<ApiResponse.SuccessBody<List<QuestionTempResponse>>> viewTempQuestionList(
			@AuthenticationPrincipal TokenUserDetails userDetails) {

		List<QuestionTempResponse> res = new ArrayList<>();
		QuestionTempResponse element =
				QuestionTempResponse.builder()
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
	public ApiResponse<ApiResponse.SuccessBody<List<QuestionResponse>>> viewReviewRequestList(
			@AuthenticationPrincipal TokenUserDetails userDetails) {

		List<QuestionResponse> res = new ArrayList<>();
		QuestionResponse element =
				QuestionResponse.builder()
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

	@GetMapping("/questions/reviewers")
	public ApiResponse<ApiResponse.SuccessBody<List<MemberResponse>>> viewReviewerList(
			@AuthenticationPrincipal TokenUserDetails userDetails, @RequestParam Long question_id) {

		List<MemberResponse> res = new ArrayList<>();
		MemberResponse element =
				MemberResponse.builder().question_id(1L).author("author").author_id(1L).build();
		res.add(element);
		return ApiResponseGenerator.success(res, HttpStatus.OK);
	}
}
