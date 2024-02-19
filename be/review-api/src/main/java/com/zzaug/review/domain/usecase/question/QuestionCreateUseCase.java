package com.zzaug.review.domain.usecase.question;

import com.zzaug.review.config.JpaDataSourceConfig;
import com.zzaug.review.domain.dto.question.QuestionCreateUseCaseRequest;
import com.zzaug.review.domain.model.question.Question;
import com.zzaug.review.domain.persistence.question.QuestionRepository;
import com.zzaug.review.domain.support.entity.QuestionEntityConverter;
import com.zzaug.review.domain.usecase.question.converter.QuestionConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionCreateUseCase {
	private final QuestionRepository questionRepository;
	private final QuestionConverter questionConverter;

	@Transactional(JpaDataSourceConfig.TRANSACTION_MANAGER_NAME)
	public void execute(QuestionCreateUseCaseRequest request) {
		Question question = questionConverter.from(request);
		questionRepository.save(QuestionEntityConverter.from(question));
	}
}
