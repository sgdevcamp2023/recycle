package com.zzaug.review.web.controller.v1.question;

import com.zzaug.review.domain.dto.question.*;
import com.zzaug.review.domain.exception.AlreadyDeletedException;
import com.zzaug.review.domain.exception.UnAuthorizationException;
import com.zzaug.review.domain.usecase.question.QuestionCreateUseCase;
import com.zzaug.review.domain.usecase.question.QuestionDeleteUseCase;
import com.zzaug.review.domain.usecase.question.QuestionTempCreateUseCase;
import com.zzaug.review.domain.usecase.question.QuestionTempViewUseCase;
import com.zzaug.review.web.dto.question.QuestionDeleteRequest;
import com.zzaug.review.web.dto.question.QuestionRequest;
import com.zzaug.review.web.dto.question.QuestionTempRequest;
import com.zzaug.review.web.support.usecase.QuestionCreateUseCaseRequestConverter;
import com.zzaug.review.web.support.usecase.QuestionDeleteUseCaseRequestConverter;
import com.zzaug.review.web.support.usecase.QuestionTempUseCaseRequestConverter;
import com.zzaug.review.web.support.usecase.QuestionTempViewUseCaseRequestConverter;
import com.zzaug.security.authentication.token.TokenUserDetails;
import com.zzaug.web.support.ApiResponse;
import com.zzaug.web.support.ApiResponseGenerator;
import com.zzaug.web.support.MessageCode;
import java.util.List;
import java.util.NoSuchElementException;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
@Validated
public class QuestionController {

	private final QuestionCreateUseCase questionCreateUseCase;
	private final QuestionTempCreateUseCase questionTempCreateUseCase;
	private final QuestionDeleteUseCase questionDeleteUseCase;
	private final QuestionTempViewUseCase questionTempViewUseCase;

	@PostMapping
	public ApiResponse<ApiResponse.Success> createQuestion(
			@RequestBody @Valid QuestionRequest request) {

		QuestionCreateUseCaseRequest useCaseRequest =
				QuestionCreateUseCaseRequestConverter.from(request, "author", 1L);
		questionCreateUseCase.execute(useCaseRequest);

		return ApiResponseGenerator.success(HttpStatus.OK, MessageCode.RESOURCE_CREATED);
	}

	@PostMapping("/temp")
	public ApiResponse<ApiResponse.Success> createTempQuestion(
			@RequestBody @Valid QuestionTempRequest request) {
		QuestionTempCreateUseCaseRequest useCaseRequest =
				QuestionTempUseCaseRequestConverter.from(request, "author", 1L);
		questionTempCreateUseCase.execute(useCaseRequest);

		return ApiResponseGenerator.success(HttpStatus.OK, MessageCode.RESOURCE_CREATED);
	}

	@DeleteMapping("/{questionId}")
	public ApiResponse<?> deleteQuestion(
			@Valid @PathVariable Long questionId) {

		QuestionDeleteRequest request =
				QuestionDeleteRequest.builder()
						.questionId(questionId)
						.authorId(1L)
						.build();

		QuestionDeleteUseCaseRequest useCaseRequest =
				QuestionDeleteUseCaseRequestConverter.from(request);

		try {
			questionDeleteUseCase.execute(useCaseRequest);
			return ApiResponseGenerator.success(HttpStatus.OK, MessageCode.RESOURCE_DELETED);
		} catch (NoSuchElementException e) {
			return ApiResponseGenerator.fail(MessageCode.RESOURCE_NOT_FOUND, HttpStatus.NOT_FOUND);
		} catch (UnAuthorizationException e) {
			return ApiResponseGenerator.fail(MessageCode.ACCESS_DENIED, HttpStatus.FORBIDDEN);
		} catch (AlreadyDeletedException e) {
			return ApiResponseGenerator.fail(MessageCode.RESOURCE_ALREADY_DELETED, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/temp")
	public ApiResponse<ApiResponse.SuccessBody<List<QuestionTempResponse>>> viewTempQuestionList(
			@RequestParam(required = false) String tempId) {

		QuestionTempViewUseCaseRequest useCaseRequest = new QuestionTempViewUseCaseRequest();

		if (tempId == null) {
			useCaseRequest =
					QuestionTempViewUseCaseRequestConverter.from(1L);

		} else {
			useCaseRequest =
					QuestionTempViewUseCaseRequestConverter.from(tempId, 1L);
		}

		List<QuestionTempResponse> res = questionTempViewUseCase.execute(useCaseRequest);

		return ApiResponseGenerator.success(res, HttpStatus.OK, MessageCode.SUCCESS);
	}
}
