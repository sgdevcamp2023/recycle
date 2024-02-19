package com.zzaug.review.domain.usecase.question.query;


import com.zzaug.review.config.JpaDataSourceConfig;
import com.zzaug.review.domain.dto.question.QuestionResponse;
import com.zzaug.review.domain.dto.question.QuestionSearchUseCaseRequest;
import com.zzaug.review.domain.persistence.question.QuestionRepository;
import com.zzaug.review.domain.usecase.question.converter.QuestionConverter;
import com.zzaug.review.domain.usecase.question.converter.QuestionResponseConverter;
import com.zzaug.review.entity.question.QuestionEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionQuerySearchUseCase {

	private final QuestionRepository questionQueryRepository;
	private final QuestionConverter questionQueryConverter;
	private final QuestionResponseConverter questionQueryResponseConverter;

	@Transactional(JpaDataSourceConfig.TRANSACTION_MANAGER_NAME)
	public Page<QuestionResponse> execute(QuestionSearchUseCaseRequest request) {
		Page<QuestionEntity> results =
				questionQueryRepository.findAllByAuthorIdAndContentContainingAndIsDeletedIsFalse(
						request.getPageRequest(), request.getAuthorId(), request.getQuery());

		Page<QuestionResponse> responses = results.map(QuestionResponseConverter::from);

		return responses;
	}
}
