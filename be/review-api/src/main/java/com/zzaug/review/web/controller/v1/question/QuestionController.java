package com.zzaug.review.web.controller.v1.question;

import com.zzaug.review.domain.dto.question.*;

import com.zzaug.review.domain.usecase.question.QuestionCreateUseCase;
import com.zzaug.review.domain.usecase.question.QuestionDeleteUseCase;
import com.zzaug.review.domain.usecase.question.QuestionTempCreateUseCase;
import com.zzaug.review.domain.usecase.question.QuestionTempViewUseCase;
import com.zzaug.review.support.ApiResponse;
import com.zzaug.review.support.ApiResponseGenerator;
import com.zzaug.review.support.MessageCode;
import com.zzaug.review.web.dto.question.QuestionDeleteRequest;
import com.zzaug.review.web.dto.question.QuestionRequest;
import com.zzaug.review.web.dto.question.QuestionTempRequest;
import com.zzaug.review.web.support.usecase.QuestionCreateUseCaseRequestConverter;
import com.zzaug.review.web.support.usecase.QuestionDeleteUseCaseRequestConverter;
import com.zzaug.review.web.support.usecase.QuestionTempUseCaseRequestConverter;
import com.zzaug.review.web.support.usecase.QuestionTempViewUseCaseRequestConverter;
import com.zzaug.security.authentication.token.TokenUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
public class QuestionController {

	private final QuestionCreateUseCase questionCreateUseCase;
	private final QuestionTempCreateUseCase questionTempCreateUseCase;
	private final QuestionDeleteUseCase questionDeleteUseCase;
	private final QuestionTempViewUseCase questionTempViewUseCase;

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
	@DeleteMapping("/{questionId}")
	public ApiResponse<?> deleteQuestion(
			@AuthenticationPrincipal TokenUserDetails userDetails, @PathVariable Long questionId) {

		QuestionDeleteRequest request = QuestionDeleteRequest.builder()
				.questionId(questionId)
				.authorId(Long.valueOf(userDetails.getId()))
				.build();

		QuestionDeleteUseCaseRequest useCaseRequest =
				QuestionDeleteUseCaseRequestConverter.from(request);

		try {
			questionDeleteUseCase.execute(useCaseRequest);
			return ApiResponseGenerator.success(HttpStatus.OK, MessageCode.RESOURCE_DELETED);
		} catch (NoSuchElementException e){
			return ApiResponseGenerator.fail(MessageCode.RESOURCE_NOT_FOUND ,HttpStatus.NOT_FOUND);
		} catch (RuntimeException e){
			return ApiResponseGenerator.fail(MessageCode.ACCESS_DENIED ,HttpStatus.FORBIDDEN);
		}
	}

	@GetMapping("/temp")
	public ApiResponse<ApiResponse.SuccessBody<List<QuestionTempResponse>>> viewTempQuestionList(
			@AuthenticationPrincipal TokenUserDetails userDetails, @RequestParam String tempId) {

		QuestionTempViewUseCaseRequest useCaseRequest = new QuestionTempViewUseCaseRequest();

		if (tempId == null) {
			 useCaseRequest =
					QuestionTempViewUseCaseRequestConverter.from(Long.valueOf(userDetails.getId()));

		} else {
			 useCaseRequest =
					QuestionTempViewUseCaseRequestConverter.from(tempId, Long.valueOf(userDetails.getId()));
		}

		List<QuestionTempResponse> res = questionTempViewUseCase.execute(useCaseRequest);

		return ApiResponseGenerator.success(res, HttpStatus.OK, MessageCode.SUCCESS);
	}

}
