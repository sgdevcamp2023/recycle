package com.zzaug.review.domain.usecase.question.query;

import com.zzaug.review.domain.dto.question.query.QuestionQueryResponse;
import com.zzaug.review.domain.dto.question.query.QuestionQueryViewUseCaseRequest;
import com.zzaug.review.domain.model.question.query.QuestionQuery;
import com.zzaug.review.domain.persistence.question.QuestionQueryRepository;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionQueryViewUseCase {

	private final QuestionQueryRepository questioinQueryRepository;
	private final QuestionQueryConverter questionQueryConverter;
	private final QuestionQueryResponseConverter questionQueryResponseConverter;

	@Transactional
	public QuestionQueryResponse execute(QuestionQueryViewUseCaseRequest request) {
		QuestionQuery result =
				questionQueryConverter.from(
						questioinQueryRepository
								.findByQuestionIdAndIsDeletedIsFalse(request.getQuestionId())
								.orElseThrow(() -> new NoSuchElementException("요청에 대한 응답을 찾을 수 없습니다.")));

		return questionQueryResponseConverter.from(result);
	}
}
