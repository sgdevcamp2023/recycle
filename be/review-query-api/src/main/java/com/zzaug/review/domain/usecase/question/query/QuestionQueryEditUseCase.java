package com.zzaug.review.domain.usecase.question.query;

import com.zzaug.review.config.JpaDataSourceConfig;
import com.zzaug.review.domain.dto.question.query.QuestionQueryEditUseCaseRequest;
import com.zzaug.review.domain.model.question.query.QuestionQuery;
import com.zzaug.review.domain.persistence.question.QuestionQueryRepository;
import com.zzaug.review.domain.support.entity.QuestionQueryEntityConverter;
import com.zzaug.review.entity.question.query.QuestionQueryEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionQueryEditUseCase {
	private final QuestionQueryRepository questionQueryRepository;
	private final QuestionQueryConverter questionQueryConverter;

	@Transactional(JpaDataSourceConfig.TRANSACTION_MANAGER_NAME)
	public void execute(QuestionQueryEditUseCaseRequest request) {
		QuestionQueryEntity question =
				questionQueryRepository
						.findById(request.getQuestionId())
						.orElseThrow(RuntimeException::new);
		QuestionQuery questionQuery = questionQueryConverter.from(request, question);
		questionQueryRepository.save(QuestionQueryEntityConverter.from(questionQuery));
	}
}
