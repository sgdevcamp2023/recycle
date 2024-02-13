package com.zzaug.review.domain.usecase.question.event;

import com.zzaug.review.domain.event.DeleteQuestionEvent;
import com.zzaug.review.domain.event.SaveQuestionEvent;
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

    @RabbitListener(queues = "zzaug.review")
    public void receiveEvent(SaveQuestionEvent event) {
        log.info("Received event: {}", event);
        publisher.publishEvent(event);
    }

    @RabbitListener(queues = "zzaug.review")
    public void receiveEvent(DeleteQuestionEvent event) {
        log.info("Received event: {}", event);
        publisher.publishEvent(event);
    }
}
