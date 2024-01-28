package com.zzaug.review.domain.usecase.question.query;

import com.zzaug.review.domain.dto.question.query.QuestionQueryResponse;
import com.zzaug.review.domain.dto.question.query.QuestionQuerySearchUseCaseRequest;
import com.zzaug.review.domain.persistence.question.QuestionQueryRepository;
import com.zzaug.review.entity.question.query.QuestionQueryEntity;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionQuerySearchUseCase {

	private final QuestionQueryRepository questionQueryRepository;
	private final QuestionQueryConverter questionQueryConverter;
	private final QuestionQueryResponseConverter questionQueryResponseConverter;

	@Transactional
	public Page<QuestionQueryResponse> execute(QuestionQuerySearchUseCaseRequest request) {
		Page<QuestionQueryEntity> results =
				questionQueryRepository.findAllByAuthorIdAndContentContaining(
						request.getPageRequest(), request.getAuthorId(), request.getQuery());

		Page<QuestionQueryResponse> responses = results.map(QuestionQueryResponseConverter::from);

		return responses;
	}
}
