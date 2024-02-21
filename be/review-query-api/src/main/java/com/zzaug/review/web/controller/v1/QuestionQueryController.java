package com.zzaug.review.web.controller.v1;

import com.zzaug.review.domain.dto.question.query.QuestionQueryResponse;
import com.zzaug.review.domain.dto.question.query.QuestionQuerySearchUseCaseRequest;
import com.zzaug.review.domain.dto.question.query.QuestionQueryViewUseCaseRequest;
import com.zzaug.review.domain.usecase.question.query.QuestionQuerySearchUseCase;
import com.zzaug.review.domain.usecase.question.query.QuestionQueryViewUseCase;
import com.zzaug.review.web.dto.question.query.QuestionQuerySearchRequest;
import com.zzaug.review.web.dto.question.query.QuestionQueryViewRequest;
import com.zzaug.review.web.support.usecase.QuestionQuerySearchUseCaseRequestConverter;
import com.zzaug.review.web.support.usecase.QuestionQueryViewUseCaseRequestConverter;
import com.zzaug.security.authentication.token.TokenUserDetails;
import com.zzaug.web.support.ApiResponse;
import com.zzaug.web.support.ApiResponseGenerator;
import com.zzaug.web.support.MessageCode;
import java.util.NoSuchElementException;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/review-query/question-query")
@RequiredArgsConstructor
@Validated
public class QuestionQueryController {

	private final QuestionQueryViewUseCase questionQueryViewUseCase;
	private final QuestionQuerySearchUseCase questionQuerySearchUseCase;

	@GetMapping("/{question_id}")
	public ApiResponse<?> viewQuestion(@PathVariable @Valid Long question_id) {
		try {
			QuestionQueryViewRequest request =
					QuestionQueryViewRequest.builder().questionId(question_id).build();

			QuestionQueryViewUseCaseRequest useCaseRequest =
					QuestionQueryViewUseCaseRequestConverter.from(request.getQuestionId());

			QuestionQueryResponse response = questionQueryViewUseCase.execute(useCaseRequest);

			return ApiResponseGenerator.success(response, HttpStatus.OK, MessageCode.SUCCESS);
		} catch (NoSuchElementException e) {
			return ApiResponseGenerator.fail(MessageCode.RESOURCE_NOT_FOUND, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/search")
	public ApiResponse<ApiResponse.SuccessBody<Page<QuestionQueryResponse>>> searchQuestion(
			@AuthenticationPrincipal TokenUserDetails userDetails,
			@RequestParam @Valid Boolean me,
			@RequestParam @Valid String query,
			@RequestParam @Valid int page,
			@RequestParam @Valid int size) {

		if (me) {
			QuestionQuerySearchRequest request =
					QuestionQuerySearchRequest.builder()
							.authorId(Long.valueOf(userDetails.getId()))
							.query(query)
							.pageRequest(PageRequest.of(page, size))
							.build();

			QuestionQuerySearchUseCaseRequest useCaseRequest =
					QuestionQuerySearchUseCaseRequestConverter.from(request);

			Page<QuestionQueryResponse> responses = questionQuerySearchUseCase.execute(useCaseRequest);
			return ApiResponseGenerator.success(responses, HttpStatus.OK, MessageCode.SUCCESS);
		}

		return ApiResponseGenerator.success(null, HttpStatus.OK, MessageCode.SUCCESS);
	}
}
