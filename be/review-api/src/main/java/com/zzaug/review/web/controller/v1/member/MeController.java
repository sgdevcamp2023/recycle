package com.zzaug.review.web.controller.v1.member;

import com.zzaug.review.domain.dto.member.MemberResponse;
import com.zzaug.review.domain.dto.member.ViewReviewerUseCaseRequest;
import com.zzaug.review.domain.dto.question.QuestionReqResponse;
import com.zzaug.review.domain.dto.question.QuestionReqViewUseCaseRequest;

import com.zzaug.review.domain.usecase.member.ViewReviewerUseCase;
import com.zzaug.review.domain.usecase.question.QuestionReqViewUseCase;
import com.zzaug.review.support.ApiResponse;
import com.zzaug.review.support.ApiResponseGenerator;
import com.zzaug.review.support.MessageCode;
import com.zzaug.review.web.support.usecase.QuestionReqViewUseCaseRequestConverter;
import com.zzaug.review.web.support.usecase.ViewReviewerUseCaseRequestConverter;
import com.zzaug.security.authentication.token.TokenUserDetails;

import java.util.List;
import java.util.NoSuchElementException;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/me")
@RequiredArgsConstructor
public class MeController {

	private final QuestionReqViewUseCase questionReqViewUseCase;
	private final ViewReviewerUseCase viewReviewerUseCase;

	@GetMapping("/questions/reviewers")
	public ApiResponse<?> viewReviewerList(
			@RequestParam Long questionId) {
		try {
			ViewReviewerUseCaseRequest useCaseRequest =
					ViewReviewerUseCaseRequestConverter.from(questionId);
			List<MemberResponse> responses = viewReviewerUseCase.execute(useCaseRequest);
			return ApiResponseGenerator.success(responses, HttpStatus.OK, MessageCode.SUCCESS);
		}catch (NoSuchElementException e) {
			return ApiResponseGenerator.fail(MessageCode.RESOURCE_NOT_FOUND, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/requests/reviews")
	public ApiResponse<?> viewReviewRequestList(
			@AuthenticationPrincipal TokenUserDetails userDetails, int page, int size) {
		try {
			QuestionReqViewUseCaseRequest useCaseRequest =
					QuestionReqViewUseCaseRequestConverter.from(Long.valueOf(userDetails.getId()));
			List<QuestionReqResponse> result = questionReqViewUseCase.execute(useCaseRequest);
			PageRequest pageRequest = PageRequest.of(page, size);
			int start = (int) pageRequest.getOffset();
			int end = Math.min((start + pageRequest.getPageSize()), result.size());

			Page<QuestionReqResponse> responses = new PageImpl<>(result.subList(start, end), pageRequest, result.size());

			return ApiResponseGenerator.success(responses, HttpStatus.OK);
		} catch (NoSuchElementException e){
			return ApiResponseGenerator.fail(MessageCode.RESOURCE_NOT_FOUND, HttpStatus.NOT_FOUND);
		}

	}

}
