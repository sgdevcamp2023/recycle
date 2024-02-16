package com.zzaug.review.domain.usecase.question;

import com.zzaug.review.config.JpaDataSourceConfig;
import com.zzaug.review.domain.event.question.QuestionRequestEvent;
import com.zzaug.review.domain.persistence.question.QuestionRequestRepository;
import com.zzaug.review.domain.support.entity.QuestionRequestEntityConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionRequestCreateUseCase {
	private final QuestionRequestRepository questionRequestRepository;

	@Transactional(JpaDataSourceConfig.TRANSACTION_MANAGER_NAME)
	@EventListener
	public void execute(QuestionRequestEvent event) {
		log.debug("Event received: {}", event);
		questionRequestRepository.save(QuestionRequestEntityConverter.from(event));
		log.debug("Event saved: {}", event);
	}
}
