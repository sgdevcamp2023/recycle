package com.zzaug.review.domain.usecase.question.event;

import com.zzaug.review.domain.event.question.QuestionRequestEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionEventListener {
    private final ApplicationEventPublisher publisher;

    @RabbitListener(containerFactory = "RabbitMQrabbitListenerContainerFactory", queues = "")
    private void receiveEvent(QuestionRequestEvent event) {
        log.debug("Event received: {}", event);
        publisher.publishEvent(event);
        log.debug("Event published: {}", event);
    }
}
