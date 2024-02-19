package com.zzaug.review.web.controller.v1.review;

import com.zzaug.review.domain.dto.review.*;
import com.zzaug.review.domain.exception.AlreadyDeletedException;
import com.zzaug.review.domain.exception.UnAuthorizationException;
import com.zzaug.review.domain.usecase.review.*;
import com.zzaug.review.domain.usecase.review.query.ReviewByQuestionUseCase;
import com.zzaug.review.domain.usecase.review.query.SearchByQuestionUseCase;
import com.zzaug.review.domain.usecase.review.query.SearchByReviewUseCase;
import com.zzaug.review.web.dto.review.*;
import com.zzaug.review.web.support.usecase.review.*;
import com.zzaug.security.authentication.token.TokenUserDetails;
import com.zzaug.web.support.ApiResponse;
import com.zzaug.web.support.ApiResponseGenerator;
import com.zzaug.web.support.MessageCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Validated
public class ReviewController {

	private final ReviewCreateUseCase reviewCreateUseCase;
	private final ReviewTempCreateUseCase reviewTempCreateUseCase;
	private final ReviewEditUseCase reviewEditUseCase;
	private final ReviewDeleteUseCase reviewDeleteUseCase;
	private final ReviewTempViewUseCase reviewTempViewUseCase;
	private final ReviewByQuestionUseCase reviewByQuestionUseCase;
	private final SearchByReviewUseCase searchByReviewUseCase;
	private final SearchByQuestionUseCase searchByQuestionUseCase;

	@PostMapping("/questions/{questionId}/reviews")
	public ApiResponse<ApiResponse.Success> createReview(
			@AuthenticationPrincipal TokenUserDetails userDetails,
			@PathVariable @Valid Long questionId,
			@RequestBody @Valid ReviewRequest request) {

		ReviewCreateUseCaseRequest useCaseRequest =
				ReviewCreateUseCaseRequestConverter.from(request, questionId, userDetails);
		reviewCreateUseCase.execute(useCaseRequest);

		return ApiResponseGenerator.success(HttpStatus.OK, MessageCode.RESOURCE_CREATED);
	}

	@PostMapping("/questions/{questionId}/reviews/temp")
	public ApiResponse<ApiResponse.Success> createTempReview(
			@AuthenticationPrincipal TokenUserDetails userDetails,
			@PathVariable @Valid Long questionId,
			@RequestBody @Valid ReviewTempRequest request) {

		ReviewTempCreateUseCaseRequest useCaseRequest =
				ReviewTempUseCaseRequestConverter.from(request, questionId, userDetails);
		reviewTempCreateUseCase.execute(useCaseRequest);

		return ApiResponseGenerator.success(HttpStatus.OK, MessageCode.RESOURCE_CREATED);
	}

	@PutMapping("/questions/{questionId}/reviews/{reviewId}")
	public ApiResponse<?> editReview(
			@AuthenticationPrincipal TokenUserDetails userDetails,
			@PathVariable @Valid Long questionId,
			@PathVariable @Valid Long reviewId,
			@RequestBody @Valid ReviewRequest request) {

		ReviewEditUseCaseRequest useCaseRequest =
				ReviewEditUseCaseRequestConverter.from(request, reviewId, questionId, userDetails);
		try {
			reviewEditUseCase.execute(useCaseRequest);
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

	@DeleteMapping("/questions/{questionId}/reviews/{reviewId}")
	public ApiResponse<?> deleteReview(
			@AuthenticationPrincipal TokenUserDetails userDetails,
			@PathVariable @Valid Long questionId,
			@PathVariable @Valid Long reviewId) {

		ReviewDeleteRequest request =
				ReviewDeleteRequest.builder()
						.reviewId(reviewId)
						.questionId(questionId)
						.authorId(Long.valueOf(userDetails.getId()))
						.build();

		ReviewDeleteUseCaseRequest useCaseRequest = ReviewDeleteUseCaseRequestConverter.from(request);

		try {
			reviewDeleteUseCase.execute(useCaseRequest);
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

	@GetMapping("/questions/{questionId}/reviews/temp")
	public ApiResponse<ApiResponse.SuccessBody<List<ReviewTempResponse>>> viewTempReviewList(
			@AuthenticationPrincipal TokenUserDetails userDetails,
			@PathVariable Long questionId,
			@RequestParam(required = false) String tempId) {

		ReviewTempViewUseCaseRequest useCaseRequest = new ReviewTempViewUseCaseRequest();

		if (tempId == null) {
			useCaseRequest = ReviewTempViewUseCaseRequestConverter.from(tempId);
		} else {
			useCaseRequest =
					ReviewTempViewUseCaseRequestConverter.from(Long.valueOf(userDetails.getId()), questionId);
		}

		List<ReviewTempResponse> responses = reviewTempViewUseCase.execute(useCaseRequest);

		return ApiResponseGenerator.success(responses, HttpStatus.OK, MessageCode.SUCCESS);
	}

	@GetMapping("/question-query/{question_id}/reviews")
	public ApiResponse<ApiResponse.SuccessBody<List<ReviewResponse>>> viewQuestionReviewList(
			@PathVariable @Valid Long question_id) {

		ReviewByQuestionUseCaseRequest useCaseRequest =
				ReviewByQuestionUseCaseRequestConverter.from(question_id);
		List<ReviewResponse> responses = reviewByQuestionUseCase.execute(useCaseRequest);

		return ApiResponseGenerator.success(responses, HttpStatus.OK, MessageCode.SUCCESS);
	}

	@GetMapping("/review-query/search")
	public ApiResponse<ApiResponse.SuccessBody<List<Map<String, Object>>>> searchReviewList(
			@AuthenticationPrincipal TokenUserDetails userDetails,
			@RequestParam @Valid Boolean me,
			@RequestParam @Valid Boolean validQuestion,
			@RequestParam @Valid String query) {

		List<Map<String, Object>> responses = new ArrayList<>();
		if (me) {

			if (validQuestion) {
				ReviewSearchByQuestionRequest request =
						ReviewSearchByQuestionRequest.builder()
								.authorId(Long.valueOf(userDetails.getId()))
								.query(query)
								.build();
				SearchByQuestionUseCaseRequest useCaseRequest =
						SearchByQuestionUseCaseRequestConverter.from(request);
				responses = searchByQuestionUseCase.execute(useCaseRequest);
				return ApiResponseGenerator.success(responses, HttpStatus.OK, MessageCode.SUCCESS);

			} else {
				ReviewSearchRequest request =
						ReviewSearchRequest.builder()
								.authorId(Long.valueOf(userDetails.getId()))
								.query(query)
								.build();
				SearchByReviewUseCaseRequest useCaseRequest =
						SearchByReviewUseCaseRequestConverter.from(request);
				responses = searchByReviewUseCase.execute(useCaseRequest);
				return ApiResponseGenerator.success(responses, HttpStatus.OK, MessageCode.SUCCESS);
			}
		}

		return ApiResponseGenerator.success(responses, HttpStatus.OK, MessageCode.SUCCESS);
	}
}
