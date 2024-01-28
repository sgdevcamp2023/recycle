package com.zzaug.review.web.controller.v1;

import com.zzaug.review.domain.dto.question.query.QuestionQueryResponse;
import com.zzaug.review.domain.dto.question.query.QuestionQueryViewUseCaseRequest;
import com.zzaug.review.domain.usecase.question.query.QuestionQueryViewUseCase;
import com.zzaug.review.support.ApiResponse;
import com.zzaug.review.support.ApiResponseGenerator;
import com.zzaug.review.support.MessageCode;
import com.zzaug.review.web.dto.question.query.QuestionViewQueryRequest;
import com.zzaug.review.web.support.usecase.QuestionQueryViewUseCaseRequestConverter;
import com.zzaug.security.authentication.token.TokenUserDetails;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/question-query")
@RequiredArgsConstructor
public class QuestionQueryController {

	private final QuestionQueryViewUseCase questionQueryViewUseCase;
	@GetMapping("/{question_id}")
	public ApiResponse<ApiResponse.SuccessBody<QuestionQueryResponse>> viewQuestion(
			@PathVariable Long question_id) {
		QuestionViewQueryRequest request =
				QuestionViewQueryRequest.builder().questionId(question_id).build();

		QuestionQueryViewUseCaseRequest useCaseRequest =
				QuestionQueryViewUseCaseRequestConverter.from(request.getQuestionId());

		QuestionQueryResponse response = questionQueryViewUseCase.execute(useCaseRequest);

		return ApiResponseGenerator.success(response, HttpStatus.OK, MessageCode.SUCCESS);
	}

	@GetMapping("/search")
	public ApiResponse<ApiResponse.SuccessBody<List<QuestionQueryResponse>>> searchQuestion(
			@AuthenticationPrincipal TokenUserDetails userDetails,
			@RequestParam Boolean me,
			@RequestParam String query,
			@RequestParam int page,
			@RequestParam int size) {

		List<QuestionQueryResponse> responses = new ArrayList<>();
		QuestionQueryResponse res =
				QuestionQueryResponse.builder()
						.question_id(1L)
						.content("content")
						.author("author")
						.author_id(1L)
						.review_cnt(1)
						.created_at(new Timestamp(System.currentTimeMillis()).toLocalDateTime())
						.updated_at(new Timestamp(System.currentTimeMillis()).toLocalDateTime())
						.build();
		responses.add(res);
		return ApiResponseGenerator.success(responses, HttpStatus.OK, MessageCode.SUCCESS);
	}
}
