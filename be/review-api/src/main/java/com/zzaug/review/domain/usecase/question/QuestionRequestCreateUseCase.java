package com.zzaug.review.domain.usecase.question;

import com.zzaug.review.domain.event.question.QuestionRequestEvent;
import com.zzaug.review.domain.persistence.question.QuestionRequestRepository;
import com.zzaug.review.domain.support.entity.QuestionRequestEntityConverter;
import com.zzaug.review.entity.question.QuestionRequestEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionRequestCreateUseCase {
    private final QuestionRequestRepository questionRequestRepository;

    @Transactional
    @EventListener
    public void execute(QuestionRequestEvent event) {
        log.debug("Event received: {}", event);
        questionRequestRepository.save(QuestionRequestEntityConverter.from(event));
        log.debug("Event saved: {}", event);
    }
}
