package com.zzaug.review.domain.usecase.question;

import com.zzaug.review.domain.dto.question.QuestionCreateUseCaseRequest;
import com.zzaug.review.domain.event.question.SaveQuestionEvent;
import com.zzaug.review.domain.model.question.Question;
import com.zzaug.review.domain.persistence.question.QuestionRepository;
import com.zzaug.review.domain.support.entity.QuestionEntityConverter;
import com.zzaug.review.domain.usecase.question.converter.QuestionConverter;
import javax.transaction.Transactional;

import com.zzaug.review.domain.usecase.question.converter.SaveQuestionEventConverter;
import com.zzaug.review.entity.question.QuestionEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionCreateUseCase {
	private final QuestionRepository questionRepository;
	private final QuestionConverter questionConverter;
	private final ApplicationEventPublisher publisher;

	@Transactional
	public void execute(QuestionCreateUseCaseRequest request) {
		Question question = questionConverter.from(request);
		QuestionEntity result = questionRepository.save(QuestionEntityConverter.from(question));
		SaveQuestionEvent event = SaveQuestionEventConverter.from(result);

		publishEvent(event);

	}

	public void publishEvent(SaveQuestionEvent event) {
		publisher.publishEvent(event);
		log.debug("Event published: {}", event);
	}
}
