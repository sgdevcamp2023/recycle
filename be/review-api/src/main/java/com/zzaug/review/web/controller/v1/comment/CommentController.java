package com.zzaug.review.web.controller.v1.comment;

import com.zzaug.review.domain.dto.comment.*;
import com.zzaug.review.domain.usecase.comment.CommentCreateUseCase;
import com.zzaug.review.domain.usecase.comment.CommentDeleteUseCase;
import com.zzaug.review.domain.usecase.comment.CommentEditUseCase;
import com.zzaug.review.domain.usecase.comment.CommentViewUseCase;
import com.zzaug.review.support.ApiResponse;
import com.zzaug.review.support.ApiResponseGenerator;
import com.zzaug.review.support.MessageCode;
import com.zzaug.review.web.dto.review.CommentRequest;
import com.zzaug.review.web.support.usecase.CommentCreateUseCaseRequestConverter;
import com.zzaug.review.web.support.usecase.CommentDeleteUseCaseRequestConverter;
import com.zzaug.review.web.support.usecase.CommentEditUseCaseRequestConverter;
import com.zzaug.review.web.support.usecase.CommentViewUseCaseRequestConverter;
import com.zzaug.security.authentication.token.TokenUserDetails;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
public class CommentController {

	private final CommentCreateUseCase commentCreateUseCase;
	private final CommentViewUseCase commentViewUseCase;
	private final CommentEditUseCase commentEditUseCase;
	private final CommentDeleteUseCase commentDeleteUseCase;

	@PostMapping("/{questionId}/comments")
	public ApiResponse<ApiResponse.SuccessBody<Void>> createComment(
			@AuthenticationPrincipal TokenUserDetails userDetails,
			@PathVariable Long questionId,
			CommentRequest request) {

		CommentCreateUseCaseRequest useCaseRequest =
				CommentCreateUseCaseRequestConverter.from(request, questionId, userDetails);
		commentCreateUseCase.execute(useCaseRequest);

		return ApiResponseGenerator.success(HttpStatus.OK, MessageCode.RESOURCE_CREATED);
	}

	@GetMapping("/{questionId}/comments")
	public ApiResponse<ApiResponse.SuccessBody<List<CommentResponse>>> viewQuestionComment(
			@AuthenticationPrincipal TokenUserDetails userDetails,
			@PathVariable Long questionId,
			CommentRequest request) {

		CommentViewUseCaseRequest useCaseRequest = CommentViewUseCaseRequestConverter.from(questionId);
		List<CommentResponse> responses = commentViewUseCase.execute(useCaseRequest);
		return ApiResponseGenerator.success(responses, HttpStatus.OK, MessageCode.SUCCESS);
	}

	@PutMapping("/{questionId}/comments/{commnetId}")
	public ApiResponse<ApiResponse.SuccessBody<Void>> editComment(
			@AuthenticationPrincipal TokenUserDetails userDetails,
			@PathVariable Long questionId,
			@PathVariable Long commnetId,
			CommentRequest request) {

		CommentEditUseCaseRequest useCaseRequest =
				CommentEditUseCaseRequestConverter.from(request, commnetId, questionId, userDetails);
		try {
			commentEditUseCase.execute(useCaseRequest);
			return ApiResponseGenerator.success(HttpStatus.OK, MessageCode.RESOURCE_MODIFIED);
		} catch (RuntimeException e) {
			return ApiResponseGenerator.success(HttpStatus.OK, MessageCode.RESOURCE_MODIFIED);
		}
	}

	@DeleteMapping("/{questionId}/comments/{commnetId}")
	public ApiResponse<ApiResponse.SuccessBody<Void>> deleteComment(
			@AuthenticationPrincipal TokenUserDetails userDetails,
			@PathVariable Long questionId,
			@PathVariable Long commnetId) {

		CommentDeleteUseCaseRequest useCaseRequest =
				CommentDeleteUseCaseRequestConverter.from(commnetId, questionId, userDetails);
		try {
			commentDeleteUseCase.execute(useCaseRequest);
			return ApiResponseGenerator.success(HttpStatus.OK, MessageCode.RESOURCE_DELETED);
		} catch (RuntimeException e) {
			return ApiResponseGenerator.success(HttpStatus.OK, MessageCode.RESOURCE_DELETED);
		}
	}
}
