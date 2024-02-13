package com.zzaug.review.domain.usecase.question.event;

import com.zzaug.review.domain.event.DeleteQuestionEvent;
import com.zzaug.review.domain.event.SaveQuestionEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@RabbitListener(queues = "zzaug.review")
public class QuestionEventListener {
    private final ApplicationEventPublisher publisher;

    @RabbitHandler
    public void receiveEvent(SaveQuestionEvent event) {
        log.info("Received event: {}", event);
        publisher.publishEvent(event);
    }

    @RabbitHandler
    public void receiveEvent(DeleteQuestionEvent event) {
        log.info("Received event: {}", event);
        publisher.publishEvent(event);
    }
}
