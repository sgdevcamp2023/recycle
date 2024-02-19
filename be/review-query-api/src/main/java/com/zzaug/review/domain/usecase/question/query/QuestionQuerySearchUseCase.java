package com.zzaug.review.domain.usecase.question.query;

import com.zzaug.review.config.JpaDataSourceConfig;
import com.zzaug.review.domain.dto.question.query.QuestionQueryResponse;
import com.zzaug.review.domain.dto.question.query.QuestionQuerySearchUseCaseRequest;
import com.zzaug.review.domain.persistence.question.QuestionSearchQueryRepository;
import com.zzaug.review.entity.question.query.QuestionQueryEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionQuerySearchUseCase {

	private final QuestionSearchQueryRepository questionQueryRepository;
	private final QuestionQueryConverter questionQueryConverter;
	private final QuestionQueryResponseConverter questionQueryResponseConverter;

	@Transactional(JpaDataSourceConfig.TRANSACTION_MANAGER_NAME)
	public Page<QuestionQueryResponse> execute(QuestionQuerySearchUseCaseRequest request) {
		Page<QuestionQueryEntity> results =
				questionQueryRepository.findAllByAuthorIdAndContentContainingAndIsDeletedIsFalse(
						request.getPageRequest(), request.getAuthorId(), request.getQuery());

		Page<QuestionQueryResponse> responses = results.map(QuestionQueryResponseConverter::from);

		return responses;
	}
}
