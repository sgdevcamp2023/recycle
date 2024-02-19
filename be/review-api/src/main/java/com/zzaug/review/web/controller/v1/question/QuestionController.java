package com.zzaug.review.web.controller.v1.question;

import com.zzaug.review.domain.dto.question.*;
import com.zzaug.review.domain.exception.AlreadyDeletedException;
import com.zzaug.review.domain.exception.UnAuthorizationException;
import com.zzaug.review.domain.usecase.question.QuestionCreateUseCase;
import com.zzaug.review.domain.usecase.question.QuestionDeleteUseCase;
import com.zzaug.review.domain.usecase.question.QuestionTempCreateUseCase;
import com.zzaug.review.domain.usecase.question.QuestionTempViewUseCase;
import com.zzaug.review.domain.usecase.question.query.QuestionQuerySearchUseCase;
import com.zzaug.review.domain.usecase.question.query.QuestionQueryViewUseCase;
import com.zzaug.review.web.dto.question.*;
import com.zzaug.review.web.support.usecase.question.*;
import com.zzaug.security.authentication.token.TokenUserDetails;
import com.zzaug.web.support.ApiResponse;
import com.zzaug.web.support.ApiResponseGenerator;
import com.zzaug.web.support.MessageCode;
import java.util.List;
import java.util.NoSuchElementException;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
	private final QuestionQueryViewUseCase questionViewUseCase;
	private final QuestionQuerySearchUseCase questionSearchUseCase;

//	@PostMapping
//	public ApiResponse<ApiResponse.Success> createQuestion(
//			@AuthenticationPrincipal TokenUserDetails userDetails,
//			@RequestBody @Valid QuestionRequest request) {
//
//		QuestionCreateUseCaseRequest useCaseRequest =
//				QuestionCreateUseCaseRequestConverter.from(request, userDetails);
//		questionCreateUseCase.execute(useCaseRequest);
//
//		return ApiResponseGenerator.success(HttpStatus.OK, MessageCode.RESOURCE_CREATED);
//	}
//
//	@PostMapping("/temp")
//	public ApiResponse<ApiResponse.Success> createTempQuestion(
//			@AuthenticationPrincipal TokenUserDetails userDetails,
//			@RequestBody @Valid QuestionTempRequest request) {
//		QuestionTempCreateUseCaseRequest useCaseRequest =
//				QuestionTempUseCaseRequestConverter.from(request, userDetails);
//		questionTempCreateUseCase.execute(useCaseRequest);
//
//		return ApiResponseGenerator.success(HttpStatus.OK, MessageCode.RESOURCE_CREATED);
//	}
//
//	@DeleteMapping("/{questionId}")
//	public ApiResponse<?> deleteQuestion(
//			@AuthenticationPrincipal TokenUserDetails userDetails, @Valid @PathVariable Long questionId) {
//
//		QuestionDeleteRequest request =
//				QuestionDeleteRequest.builder()
//						.questionId(questionId)
//						.authorId(Long.valueOf(userDetails.getId()))
//						.build();
//
//		QuestionDeleteUseCaseRequest useCaseRequest =
//				QuestionDeleteUseCaseRequestConverter.from(request);
//
//		try {
//			questionDeleteUseCase.execute(useCaseRequest);
//			return ApiResponseGenerator.success(HttpStatus.OK, MessageCode.RESOURCE_DELETED);
//		} catch (NoSuchElementException e) {
//			return ApiResponseGenerator.fail(MessageCode.RESOURCE_NOT_FOUND, HttpStatus.NOT_FOUND);
//		} catch (UnAuthorizationException e) {
//			return ApiResponseGenerator.fail(MessageCode.ACCESS_DENIED, HttpStatus.FORBIDDEN);
//		} catch (AlreadyDeletedException e) {
//			return ApiResponseGenerator.fail(MessageCode.RESOURCE_ALREADY_DELETED, HttpStatus.NOT_FOUND);
//		}
//	}
//
//	@GetMapping("/temp")
//	public ApiResponse<ApiResponse.SuccessBody<List<QuestionTempResponse>>> viewTempQuestionList(
//			@AuthenticationPrincipal TokenUserDetails userDetails,
//			@Valid @RequestParam @NotEmpty String tempId) {
//
//		QuestionTempViewUseCaseRequest useCaseRequest = new QuestionTempViewUseCaseRequest();
//
//		if (tempId == null) {
//			useCaseRequest =
//					QuestionTempViewUseCaseRequestConverter.from(Long.valueOf(userDetails.getId()));
//
//		} else {
//			useCaseRequest =
//					QuestionTempViewUseCaseRequestConverter.from(tempId, Long.valueOf(userDetails.getId()));
//		}
//
//		List<QuestionTempResponse> res = questionTempViewUseCase.execute(useCaseRequest);
//
//		return ApiResponseGenerator.success(res, HttpStatus.OK, MessageCode.SUCCESS);
//	}
//
//	@GetMapping("/{question_id}")
//	public ApiResponse<?> viewQuestion(@PathVariable @Valid Long question_id) {
//		try {
//			QuestionViewRequest request =
//					QuestionViewRequest.builder().questionId(question_id).build();
//
//			QuestionViewUseCaseRequest useCaseRequest =
//					QuestionViewUseCaseRequestConverter.from(request.getQuestionId());
//
//			QuestionResponse response = questionViewUseCase.execute(useCaseRequest);
//
//			return ApiResponseGenerator.success(response, HttpStatus.OK, MessageCode.SUCCESS);
//		} catch (NoSuchElementException e) {
//			return ApiResponseGenerator.fail(MessageCode.RESOURCE_NOT_FOUND, HttpStatus.NOT_FOUND);
//		}
//	}
//
//	@GetMapping("/search")
//	public ApiResponse<ApiResponse.SuccessBody<Page<QuestionResponse>>> searchQuestion(
//			@AuthenticationPrincipal TokenUserDetails userDetails,
//			@RequestParam @Valid Boolean me,
//			@RequestParam @Valid String query,
//			@RequestParam @Valid int page,
//			@RequestParam @Valid int size) {
//
//		if (me) {
//			QuestionSearchRequest request =
//					QuestionSearchRequest.builder()
//							.authorId(Long.valueOf(userDetails.getId()))
//							.query(query)
//							.pageRequest(PageRequest.of(page, size))
//							.build();
//
//			QuestionSearchUseCaseRequest useCaseRequest =
//					QuestionSearchUseCaseRequestConverter.from(request);
//
//			Page<QuestionResponse> responses = questionSearchUseCase.execute(useCaseRequest);
//			return ApiResponseGenerator.success(responses, HttpStatus.OK, MessageCode.SUCCESS);
//		}
//
//		return ApiResponseGenerator.success(null, HttpStatus.OK, MessageCode.SUCCESS);
//	}

	@PostMapping
	public ApiResponse<ApiResponse.Success> createQuestion(
			@RequestBody @Valid QuestionRequest request) {

		QuestionCreateUseCaseRequest useCaseRequest =
				QuestionCreateUseCaseRequestConverter.from(request, "username", 1L);
		questionCreateUseCase.execute(useCaseRequest);

		return ApiResponseGenerator.success(HttpStatus.OK, MessageCode.RESOURCE_CREATED);
	}

	@PostMapping("/temp")
	public ApiResponse<ApiResponse.Success> createTempQuestion(
			@RequestBody @Valid QuestionTempRequest request) {
		QuestionTempCreateUseCaseRequest useCaseRequest =
				QuestionTempUseCaseRequestConverter.from(request, "username", 1L);
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
			@Valid @RequestParam @NotEmpty String tempId) {

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

	@GetMapping("/{question_id}")
	public ApiResponse<?> viewQuestion(@PathVariable @Valid Long question_id) {
		try {
			QuestionViewRequest request =
					QuestionViewRequest.builder().questionId(question_id).build();

			QuestionViewUseCaseRequest useCaseRequest =
					QuestionViewUseCaseRequestConverter.from(request.getQuestionId());

			QuestionResponse response = questionViewUseCase.execute(useCaseRequest);

			return ApiResponseGenerator.success(response, HttpStatus.OK, MessageCode.SUCCESS);
		} catch (NoSuchElementException e) {
			return ApiResponseGenerator.fail(MessageCode.RESOURCE_NOT_FOUND, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/search")
	public ApiResponse<ApiResponse.SuccessBody<Page<QuestionResponse>>> searchQuestion(
			@RequestParam @Valid Boolean me,
			@RequestParam @Valid String query,
			@RequestParam @Valid int page,
			@RequestParam @Valid int size) {

		if (me) {
			QuestionSearchRequest request =
					QuestionSearchRequest.builder()
							.authorId(1L)
							.query(query)
							.pageRequest(PageRequest.of(page, size))
							.build();

			QuestionSearchUseCaseRequest useCaseRequest =
					QuestionSearchUseCaseRequestConverter.from(request);

			Page<QuestionResponse> responses = questionSearchUseCase.execute(useCaseRequest);
			return ApiResponseGenerator.success(responses, HttpStatus.OK, MessageCode.SUCCESS);
		}

		return ApiResponseGenerator.success(null, HttpStatus.OK, MessageCode.SUCCESS);
	}
}
