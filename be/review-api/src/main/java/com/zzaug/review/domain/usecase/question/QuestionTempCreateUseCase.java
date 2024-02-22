package com.zzaug.review.domain.usecase.question;

import com.zzaug.review.config.JpaDataSourceConfig;
import com.zzaug.review.domain.dto.question.QuestionTempCreateUseCaseRequest;
import com.zzaug.review.domain.model.question.QuestionTemp;
import com.zzaug.review.domain.persistence.question.QuestionTempRepository;
import com.zzaug.review.domain.support.entity.QuestionTempEntityConverter;
import com.zzaug.review.domain.usecase.question.converter.QuestionTempConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionTempCreateUseCase {
	private final QuestionTempRepository questionTempRepository;
	private final QuestionTempConverter questionTempConverter;

	@Transactional(JpaDataSourceConfig.TRANSACTION_MANAGER_NAME)
	public void execute(QuestionTempCreateUseCaseRequest request) {
		QuestionTemp questionTemp = questionTempConverter.from(request);
		questionTempRepository.save(QuestionTempEntityConverter.from(questionTemp));
	}
}
