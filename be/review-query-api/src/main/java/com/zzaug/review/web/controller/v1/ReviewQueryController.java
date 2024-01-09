package com.zzaug.review.web.controller.v1;

import com.zzaug.review.domain.dto.review.query.ReviewQueryResponse;
import com.zzaug.review.domain.dto.review.query.ReviewQueryUseCaseRequest;
import com.zzaug.review.domain.usecase.review.query.ReviewQueryUseCase;
import com.zzaug.review.support.ApiResponse;
import com.zzaug.review.support.ApiResponseGenerator;
import com.zzaug.review.web.dto.review.query.ReviewQueryRequest;
import com.zzaug.review.web.support.usecase.ReviewUseCaseRequestConverter;
import com.zzaug.security.authentication.token.TokenUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reviews-query")
@RequiredArgsConstructor
public class ReviewQueryController {

	private final ReviewQueryUseCase reviewUseCase;

	@GetMapping()
	public ApiResponse<ApiResponse.SuccessBody<ReviewQueryResponse>> foo(
			@AuthenticationPrincipal TokenUserDetails userDetails, ReviewQueryRequest request) {
		ReviewQueryUseCaseRequest useCaseRequest = ReviewUseCaseRequestConverter.from(request);
		//		ReviewResponse res= ReviewResponse.builder().name("name").build();
		ReviewQueryResponse res = ReviewQueryResponse.builder().name("name").build();
		return ApiResponseGenerator.success(res, HttpStatus.OK);
	}
}
