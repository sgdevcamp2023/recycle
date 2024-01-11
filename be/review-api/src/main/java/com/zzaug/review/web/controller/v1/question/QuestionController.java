package com.zzaug.review.web.controller.v1.question;

import com.zzaug.review.domain.dto.question.QuestionResponse;
import com.zzaug.review.domain.dto.question.QuestionTempUseCaseRequest;
import com.zzaug.review.domain.dto.question.QuestionUseCaseRequest;
import com.zzaug.review.domain.usecase.question.QuestionUseCase;
import com.zzaug.review.support.ApiResponse;
import com.zzaug.review.support.ApiResponseGenerator;
import com.zzaug.review.web.dto.question.QuestionRequest;
import com.zzaug.review.web.dto.question.QuestionTempRequest;
import com.zzaug.review.web.support.usecase.QuestionTempUseCaseRequestConverter;
import com.zzaug.review.web.support.usecase.QuestionUseCaseRequestConverter;
import com.zzaug.security.authentication.token.TokenUserDetails;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
public class QuestionController {

	private final QuestionUseCase questionUseCase;

	@PostMapping
	public ApiResponse<ApiResponse.SuccessBody<Void>> createQuestion(
			@AuthenticationPrincipal TokenUserDetails userDetails, QuestionRequest request) {
		QuestionUseCaseRequest useCaseRequest = QuestionUseCaseRequestConverter.from(request);

		return ApiResponseGenerator.success(HttpStatus.OK);
	}

	@PostMapping("/temp")
	public ApiResponse<ApiResponse.SuccessBody<Void>> createTempQuestion(
			@AuthenticationPrincipal TokenUserDetails userDetails, QuestionTempRequest request) {
		QuestionTempUseCaseRequest useCaseRequest = QuestionTempUseCaseRequestConverter.from(request);

		return ApiResponseGenerator.success(HttpStatus.OK);
	}

	@GetMapping("/{question_id}")
	public ApiResponse<ApiResponse.SuccessBody<QuestionResponse>> viewQuestion(
			@AuthenticationPrincipal TokenUserDetails userDetails, @PathVariable Long question_id) {

		QuestionResponse res =
				QuestionResponse.builder()
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
	public ApiResponse<ApiResponse.SuccessBody<List<QuestionResponse>>> searchQuestion(
			@AuthenticationPrincipal TokenUserDetails userDetails,
			@RequestParam Boolean me,
			@RequestParam String query,
			@RequestParam int page,
			@RequestParam int size) {

		List<QuestionResponse> responses = new ArrayList<>();
		QuestionResponse res =
				QuestionResponse.builder()
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

	@DeleteMapping("/{question_id}")
	public ApiResponse<ApiResponse.SuccessBody<Void>> deleteQuestion(
			@AuthenticationPrincipal TokenUserDetails userDetails, @PathVariable Long question_id) {

		return ApiResponseGenerator.success(HttpStatus.OK);
	}
}
