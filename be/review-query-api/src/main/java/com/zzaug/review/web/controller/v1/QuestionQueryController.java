package com.zzaug.review.web.controller.v1;

import com.zzaug.review.domain.dto.question.query.QuestionQueryResponse;
import com.zzaug.review.support.ApiResponse;
import com.zzaug.review.support.ApiResponseGenerator;
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
public class QuestionQueryController {
	@GetMapping("/{question_id}")
	public ApiResponse<ApiResponse.SuccessBody<QuestionQueryResponse>> viewQuestion(
			@AuthenticationPrincipal TokenUserDetails userDetails, @PathVariable Long question_id) {

		QuestionQueryResponse res =
				QuestionQueryResponse.builder()
						.question_id(1L)
						.content("content")
						.author("author")
						.author_id(1L)
						.review_cnt(1)
						.created_at(new Timestamp(System.currentTimeMillis()))
						.updated_at(new Timestamp(System.currentTimeMillis()))
						.build();
		return ApiResponseGenerator.success(res, HttpStatus.OK);
	}

	@GetMapping("/search")
	public ApiResponse<ApiResponse.SuccessBody<List<QuestionQueryResponse>>> searchQuestion(
			@AuthenticationPrincipal TokenUserDetails userDetails,
			@RequestParam Boolean me,
			@RequestParam String query,
			@RequestParam int page,
			@RequestParam int size) {

		List<QuestionQueryResponse> responses = new ArrayList<>();
		QuestionQueryResponse res =
				QuestionQueryResponse.builder()
						.question_id(1L)
						.content("content")
						.author("author")
						.author_id(1L)
						.review_cnt(1)
						.created_at(new Timestamp(System.currentTimeMillis()))
						.updated_at(new Timestamp(System.currentTimeMillis()))
						.build();
		responses.add(res);
		return ApiResponseGenerator.success(responses, HttpStatus.OK);
	}
}
