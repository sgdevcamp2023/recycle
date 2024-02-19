package com.zzaug.review.domain.usecase.question.query;

import com.zzaug.review.config.JpaDataSourceConfig;
import com.zzaug.review.config.ReviewAppConfig;
import com.zzaug.review.domain.event.question.SaveQuestionEvent;
import com.zzaug.review.domain.model.question.query.QuestionQuery;
import com.zzaug.review.domain.persistence.question.QuestionQueryRepository;
import com.zzaug.review.domain.support.entity.QuestionQueryEntityConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionQueryCreateUseCase {

	private final QuestionQueryRepository questionQueryRepository;
	private final QuestionQueryConverter questionQueryConverter;

	@Transactional(JpaDataSourceConfig.TRANSACTION_MANAGER_NAME)
	@EventListener
	public void execute(SaveQuestionEvent event) {
		QuestionQuery questionQuery = questionQueryConverter.from(event);
		questionQueryRepository.save(QuestionQueryEntityConverter.from(questionQuery));
	}
}
