package com.zzaug.review.domain.usecase.question.query;

import com.zzaug.review.config.JpaDataSourceConfig;
import com.zzaug.review.domain.dto.question.QuestionResponse;
import com.zzaug.review.domain.dto.question.QuestionViewUseCaseRequest;

import com.zzaug.review.domain.model.question.Question;

import java.util.NoSuchElementException;

import com.zzaug.review.domain.persistence.question.QuestionRepository;
import com.zzaug.review.domain.usecase.question.converter.QuestionConverter;
import com.zzaug.review.domain.usecase.question.converter.QuestionResponseConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionQueryViewUseCase {

	private final QuestionRepository questioinQueryRepository;
	private final QuestionConverter questionQueryConverter;
	private final QuestionResponseConverter questionQueryResponseConverter;

	@Transactional(JpaDataSourceConfig.TRANSACTION_MANAGER_NAME)
	public QuestionResponse execute(QuestionViewUseCaseRequest request) {
		Question result =
				questionQueryConverter.from(
						questioinQueryRepository
								.findByQuestionIdAndIsDeletedIsFalse(request.getQuestionId())
								.orElseThrow(() -> new NoSuchElementException("요청에 대한 응답을 찾을 수 없습니다.")));

		return questionQueryResponseConverter.from(result);
	}
}
