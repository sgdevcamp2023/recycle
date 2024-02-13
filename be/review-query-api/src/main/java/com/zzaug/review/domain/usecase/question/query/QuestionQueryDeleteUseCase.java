package com.zzaug.review.domain.usecase.question.query;

import com.zzaug.review.domain.dto.question.query.QuestionQueryDeleteUseCaseRequest;
import com.zzaug.review.domain.event.DeleteQuestionEvent;
import com.zzaug.review.domain.model.question.query.QuestionQuery;
import com.zzaug.review.domain.persistence.question.QuestionQueryRepository;
import javax.transaction.Transactional;

import com.zzaug.review.domain.support.entity.QuestionQueryEntityConverter;
import com.zzaug.review.entity.question.query.QuestionQueryEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionQueryDeleteUseCase {
	private final QuestionQueryRepository questionQueryRepository;
	private final QuestionQueryConverter questionQueryConverter;

	@Transactional
	@EventListener
	public void execute(DeleteQuestionEvent event) {
		log.debug("Received event: {}", event);
		QuestionQuery questionQuery = questionQueryConverter.from(event);
		questionQueryRepository.save(QuestionQueryEntityConverter.from(questionQuery));
		log.debug("QuestionQuery deleted: {}", questionQuery);
	}
}
