package com.zzaug.review.domain.usecase.question.query;

import com.zzaug.review.config.JpaDataSourceConfig;
import com.zzaug.review.domain.event.question.DeleteQuestionEvent;
import com.zzaug.review.domain.model.question.query.QuestionQuery;
import com.zzaug.review.domain.persistence.question.QuestionQueryRepository;

import com.zzaug.review.domain.support.entity.QuestionQueryEntityConverter;
import com.zzaug.review.entity.question.query.QuestionQueryEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionQueryDeleteUseCase {
	private final QuestionQueryRepository questionQueryRepository;
	private final QuestionQueryConverter questionQueryConverter;

	@Transactional(JpaDataSourceConfig.TRANSACTION_MANAGER_NAME)
	@EventListener
	public void execute(DeleteQuestionEvent event) {
		log.debug("Received event: {}", event);
		QuestionQueryEntity target = questionQueryRepository.findByQuestionId(event.getQuestionId())
				.orElseThrow(() -> new NoSuchElementException("요청에 대한 응답을 찾을 수 없습니다."));
		QuestionQuery questionQuery = questionQueryConverter.from(event, target);
		questionQueryRepository.save(QuestionQueryEntityConverter.from(questionQuery));
		log.debug("QuestionQuery deleted: {}", questionQuery);
	}
}
