package com.zzaug.review.web.controller.v1;

import com.zzaug.review.domain.dto.question.query.QuestionQueryResponse;
import com.zzaug.review.domain.dto.review.query.ViewMemberQuestionUseCaseRequest;
import com.zzaug.review.domain.dto.review.query.ViewMemberReviewUseCaseRequest;
import com.zzaug.review.domain.usecase.question.query.ViewMemberQuestionUseCase;
import com.zzaug.review.domain.usecase.review.query.ViewMemberReviewUseCase;
import com.zzaug.review.web.support.usecase.ViewMemberQuestionUseCaseRequestConverter;
import com.zzaug.review.web.support.usecase.ViewMemberReviewUseCaseRequestConverter;
import com.zzaug.security.authentication.token.TokenUserDetails;
import com.zzaug.web.support.ApiResponse;
import com.zzaug.web.support.ApiResponseGenerator;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/review-query/me-query")
@RequiredArgsConstructor
@Validated
public class MeQueryController {

	private final ViewMemberReviewUseCase viewMemberReviewUseCase;
	private final ViewMemberQuestionUseCase viewMemberQuestionUseCase;

	@GetMapping("/reviews")
	public ApiResponse<ApiResponse.SuccessBody<Page<Map<String, Object>>>> viewMemberReviewList(
			@AuthenticationPrincipal TokenUserDetails userDetails,
			@RequestParam @Valid int page,
			@RequestParam @Valid int size) {
		ViewMemberReviewUseCaseRequest useCaseRequest =
				ViewMemberReviewUseCaseRequestConverter.from(Long.valueOf(userDetails.getId()));
		List<Map<String, Object>> responseList = viewMemberReviewUseCase.execute(useCaseRequest);

		PageRequest pageRequest = PageRequest.of(page, size);
		int start = (int) pageRequest.getOffset();
		int end = Math.min((start + pageRequest.getPageSize()), responseList.size());

		Page<Map<String, Object>> responses =
				new PageImpl<>(responseList.subList(start, end), pageRequest, responseList.size());

		return ApiResponseGenerator.success(responses, HttpStatus.OK);
	}

	@GetMapping("/questions")
	public ApiResponse<ApiResponse.SuccessBody<Page<QuestionQueryResponse>>> viewMemberQuestionList(
			@AuthenticationPrincipal TokenUserDetails userDetails,
			@RequestParam @Valid int page,
			@RequestParam @Valid int size) {
		ViewMemberQuestionUseCaseRequest useCaseRequest =
				ViewMemberQuestionUseCaseRequestConverter.from(Long.valueOf(userDetails.getId()));
		List<QuestionQueryResponse> responseList = viewMemberQuestionUseCase.execute(useCaseRequest);

		PageRequest pageRequest = PageRequest.of(page, size);
		int start = (int) pageRequest.getOffset();
		int end = Math.min((start + pageRequest.getPageSize()), responseList.size());

		Page<QuestionQueryResponse> responses =
				new PageImpl<>(responseList.subList(start, end), pageRequest, responseList.size());

		return ApiResponseGenerator.success(responses, HttpStatus.OK);
	}
}
