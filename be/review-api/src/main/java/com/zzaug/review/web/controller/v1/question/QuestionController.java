package com.zzaug.review.web.controller.v1.question;

import com.zzaug.review.domain.dto.question.QuestionCreateUseCaseRequest;
import com.zzaug.review.domain.dto.question.QuestionDeleteUseCaseRequest;
import com.zzaug.review.domain.dto.question.QuestionTempCreateUseCaseRequest;
import com.zzaug.review.domain.exception.DataNotFoundException;
import com.zzaug.review.domain.exception.UnauthorizedAuthorException;
import com.zzaug.review.domain.usecase.question.QuestionCreateUseCase;
import com.zzaug.review.domain.usecase.question.QuestionDeleteUseCase;
import com.zzaug.review.domain.usecase.question.QuestionTempCreateUseCase;
import com.zzaug.review.support.ApiResponse;
import com.zzaug.review.support.ApiResponseGenerator;
import com.zzaug.review.support.MessageCode;
import com.zzaug.review.web.dto.question.QuestionDeleteRequest;
import com.zzaug.review.web.dto.question.QuestionRequest;
import com.zzaug.review.web.dto.question.QuestionTempRequest;
import com.zzaug.review.web.support.usecase.QuestionCreateUseCaseRequestConverter;
import com.zzaug.review.web.support.usecase.QuestionDeleteUseCaseRequestConverter;
import com.zzaug.review.web.support.usecase.QuestionTempUseCaseRequestConverter;
import com.zzaug.security.authentication.token.TokenUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
public class QuestionController {

	private final QuestionCreateUseCase questionCreateUseCase;
	private final QuestionTempCreateUseCase questionTempCreateUseCase;
	private final QuestionDeleteUseCase questionDeleteUseCase;

	@PostMapping
	public ApiResponse<ApiResponse.SuccessBody<Void>> createQuestion(
			@AuthenticationPrincipal TokenUserDetails userDetails, @RequestBody QuestionRequest request) {

	QuestionCreateUseCaseRequest useCaseRequest =
				QuestionCreateUseCaseRequestConverter.from(request, userDetails);
		questionCreateUseCase.execute(useCaseRequest);

		return ApiResponseGenerator.success(HttpStatus.OK, MessageCode.RESOURCE_CREATED);
	}

	@PostMapping("/temp")
	public ApiResponse<ApiResponse.SuccessBody<Void>> createTempQuestion(
			@AuthenticationPrincipal TokenUserDetails userDetails,
			@RequestBody QuestionTempRequest request) {
		QuestionTempCreateUseCaseRequest useCaseRequest =
				QuestionTempUseCaseRequestConverter.from(request, userDetails);
		questionTempCreateUseCase.execute(useCaseRequest);

		return ApiResponseGenerator.success(HttpStatus.OK, MessageCode.RESOURCE_CREATED);
}

	@DeleteMapping("/{question_id}")
	public ApiResponse<?> deleteQuestion(
			@AuthenticationPrincipal TokenUserDetails userDetails, @PathVariable Long question_id) {

		QuestionDeleteRequest request = QuestionDeleteRequest.builder()
				.question_id(question_id)
				.author_id(Long.valueOf(userDetails.getId()))
				.build();

		QuestionDeleteUseCaseRequest useCaseRequest =
				QuestionDeleteUseCaseRequestConverter.from(request);

		try {
			questionDeleteUseCase.execute(useCaseRequest);
			return ApiResponseGenerator.success(HttpStatus.OK, MessageCode.RESOURCE_DELETED);
		} catch (DataNotFoundException e){
			System.out.println("DataNotFoundException 발생: " + e.getMessage());
			return ApiResponseGenerator.success(HttpStatus.OK, MessageCode.RESOURCE_DELETED);
		}catch (UnauthorizedAuthorException e){
			System.out.println("UnauthorizedAuthorException 발생: " + e.getMessage());
			return ApiResponseGenerator.success(HttpStatus.OK, MessageCode.RESOURCE_DELETED);

		}

	}
}
