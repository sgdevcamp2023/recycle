package com.zzaug.review.web.controller.v1.comment;

import com.zzaug.review.domain.dto.comment.*;
import com.zzaug.review.domain.exception.AlreadyDeletedException;
import com.zzaug.review.domain.exception.UnAuthorizationException;
import com.zzaug.review.domain.usecase.comment.CommentCreateUseCase;
import com.zzaug.review.domain.usecase.comment.CommentDeleteUseCase;
import com.zzaug.review.domain.usecase.comment.CommentEditUseCase;
import com.zzaug.review.domain.usecase.comment.CommentViewUseCase;
import com.zzaug.review.web.dto.review.CommentRequest;
import com.zzaug.review.web.support.usecase.CommentCreateUseCaseRequestConverter;
import com.zzaug.review.web.support.usecase.CommentDeleteUseCaseRequestConverter;
import com.zzaug.review.web.support.usecase.CommentEditUseCaseRequestConverter;
import com.zzaug.review.web.support.usecase.CommentViewUseCaseRequestConverter;
import com.zzaug.security.authentication.token.TokenUserDetails;
import com.zzaug.web.support.ApiResponse;
import com.zzaug.web.support.ApiResponseGenerator;
import com.zzaug.web.support.MessageCode;
import java.util.List;
import java.util.NoSuchElementException;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
@Validated
public class CommentController {

	private final CommentCreateUseCase commentCreateUseCase;
	private final CommentViewUseCase commentViewUseCase;
	private final CommentEditUseCase commentEditUseCase;
	private final CommentDeleteUseCase commentDeleteUseCase;

	@PostMapping("/{questionId}/comments")
	public ApiResponse<ApiResponse.Success> createComment(
			@PathVariable @Valid Long questionId,
			@RequestBody @Valid CommentRequest request) {

		CommentCreateUseCaseRequest useCaseRequest =
				CommentCreateUseCaseRequestConverter.from(request, questionId, "author", 1L);
		commentCreateUseCase.execute(useCaseRequest);

		return ApiResponseGenerator.success(HttpStatus.OK, MessageCode.RESOURCE_CREATED);
	}

	@GetMapping("/{questionId}/comments")
	public ApiResponse<ApiResponse.SuccessBody<List<CommentResponse>>> viewQuestionComment(
			 @PathVariable @Valid Long questionId) {

		CommentViewUseCaseRequest useCaseRequest = CommentViewUseCaseRequestConverter.from(questionId);
		List<CommentResponse> responses = commentViewUseCase.execute(useCaseRequest);
		return ApiResponseGenerator.success(responses, HttpStatus.OK, MessageCode.SUCCESS);
	}

	@PutMapping("/{questionId}/comments/{commnetId}")
	public ApiResponse<?> editComment(
			@PathVariable @Valid Long questionId,
			@PathVariable @Valid Long commnetId,
			@RequestBody @Valid CommentRequest request) {

		CommentEditUseCaseRequest useCaseRequest =
				CommentEditUseCaseRequestConverter.from(request, commnetId, questionId, "author", 1L);
		try {
			commentEditUseCase.execute(useCaseRequest);
			return ApiResponseGenerator.success(HttpStatus.OK, MessageCode.RESOURCE_MODIFIED);
		} catch (NoSuchElementException e) {
			return ApiResponseGenerator.fail(MessageCode.RESOURCE_NOT_FOUND, HttpStatus.NOT_FOUND);
		} catch (UnAuthorizationException e) {
			return ApiResponseGenerator.fail(MessageCode.ACCESS_DENIED, HttpStatus.FORBIDDEN);
		} catch (AlreadyDeletedException e) {
			return ApiResponseGenerator.fail(
					MessageCode.RESOURCE_ALREADY_DELETED, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/{questionId}/comments/{commnetId}")
	public ApiResponse<?> deleteComment(
			@PathVariable @Valid Long questionId,
			@PathVariable @Valid Long commnetId) {

		CommentDeleteUseCaseRequest useCaseRequest =
				CommentDeleteUseCaseRequestConverter.from(commnetId, questionId, 1L);
		try {
			commentDeleteUseCase.execute(useCaseRequest);
			return ApiResponseGenerator.success(HttpStatus.OK, MessageCode.RESOURCE_DELETED);
		} catch (NoSuchElementException e) {
			return ApiResponseGenerator.fail(MessageCode.RESOURCE_NOT_FOUND, HttpStatus.NOT_FOUND);
		} catch (UnAuthorizationException e) {
			return ApiResponseGenerator.fail(MessageCode.ACCESS_DENIED, HttpStatus.FORBIDDEN);
		} catch (AlreadyDeletedException e) {
			return ApiResponseGenerator.fail(
					MessageCode.RESOURCE_ALREADY_DELETED, HttpStatus.BAD_REQUEST);
		}
	}
}
