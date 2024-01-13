package com.zzaug.review.web.controller.v1.question;

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
			@AuthenticationPrincipal TokenUserDetails userDetails, @RequestBody QuestionRequest request) {
		QuestionUseCaseRequest useCaseRequest = QuestionUseCaseRequestConverter.from(request);

		return ApiResponseGenerator.success(HttpStatus.OK);
	}

	@PostMapping("/temp")
	public ApiResponse<ApiResponse.SuccessBody<Void>> createTempQuestion(
			@AuthenticationPrincipal TokenUserDetails userDetails,
			@RequestBody QuestionTempRequest request) {
		QuestionTempUseCaseRequest useCaseRequest = QuestionTempUseCaseRequestConverter.from(request);

		return ApiResponseGenerator.success(HttpStatus.OK);
	}

	@DeleteMapping("/{question_id}")
	public ApiResponse<ApiResponse.SuccessBody<Void>> deleteQuestion(
			@AuthenticationPrincipal TokenUserDetails userDetails, @PathVariable Long question_id) {

		return ApiResponseGenerator.success(HttpStatus.OK);
	}
}
