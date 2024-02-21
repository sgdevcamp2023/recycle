package com.zzaug.review.domain.usecase.question.query;

import com.zzaug.review.config.JpaDataSourceConfig;
import com.zzaug.review.domain.dto.question.query.QuestionQueryResponse;
import com.zzaug.review.domain.dto.question.query.QuestionQueryViewUseCaseRequest;
import com.zzaug.review.domain.model.question.query.QuestionQuery;
import com.zzaug.review.domain.persistence.question.QuestionQueryRepository;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionQueryViewUseCase {

	private final QuestionQueryRepository questioinQueryRepository;
	private final QuestionQueryConverter questionQueryConverter;
	private final QuestionQueryResponseConverter questionQueryResponseConverter;

	@Transactional(JpaDataSourceConfig.TRANSACTION_MANAGER_NAME)
	public QuestionQueryResponse execute(QuestionQueryViewUseCaseRequest request) {
		QuestionQuery result =
				questionQueryConverter.from(
						questioinQueryRepository.findByQuestionIdAndIsDeletedIsFalse(request.getQuestionId()));
		if (result == null) {
			throw new NoSuchElementException("요청에 대한 응답을 찾을 수 없습니다.");
		}
		return questionQueryResponseConverter.from(result);
	}
}
