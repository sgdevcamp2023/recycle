package com.zzaug.review.web.controller.v1;

import com.zzaug.review.domain.dto.review.query.*;
import com.zzaug.review.domain.usecase.review.query.ReviewByQuestionUseCase;
import com.zzaug.review.domain.usecase.review.query.SearchByQuestionUseCase;
import com.zzaug.review.domain.usecase.review.query.SearchByReviewUseCase;
import com.zzaug.review.web.dto.review.query.ReviewSearchByQuestionRequest;
import com.zzaug.review.web.dto.review.query.ReviewSearchRequest;
import com.zzaug.review.web.support.usecase.ReviewByQuestionUseCaseRequestConverter;
import com.zzaug.review.web.support.usecase.SearchByQuestionUseCaseRequestConverter;
import com.zzaug.review.web.support.usecase.SearchByReviewUseCaseRequestConverter;
import com.zzaug.security.authentication.token.TokenUserDetails;
import com.zzaug.web.support.ApiResponse;
import com.zzaug.web.support.ApiResponseGenerator;
import com.zzaug.web.support.MessageCode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/review-query/")
@RequiredArgsConstructor
@Validated
public class ReviewQueryController {

	private final ReviewByQuestionUseCase reviewByQuestionUseCase;
	private final SearchByReviewUseCase searchByReviewUseCase;
	private final SearchByQuestionUseCase searchByQuestionUseCase;

	@GetMapping("/question-query/{question_id}/reviews")
	public ApiResponse<ApiResponse.SuccessBody<List<ReviewQueryResponse>>> viewQuestionReviewList(
			@PathVariable @Valid Long question_id) {

		ReviewByQuestionUseCaseRequest useCaseRequest =
				ReviewByQuestionUseCaseRequestConverter.from(question_id);
		List<ReviewQueryResponse> responses = reviewByQuestionUseCase.execute(useCaseRequest);

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
