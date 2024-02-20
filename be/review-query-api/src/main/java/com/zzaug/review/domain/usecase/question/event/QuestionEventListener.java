package com.zzaug.review.domain.usecase.question.event;

import com.zzaug.rabbitmq.config.ZRMQListenerConfig;
import com.zzaug.review.domain.event.question.DeleteQuestionEvent;
import com.zzaug.review.domain.event.question.SaveQuestionEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionEventListener {
    private final ApplicationEventPublisher publisher;

    @RabbitListener(containerFactory = ZRMQListenerConfig.RABBIT_LISTENER_CONTAINER_FACTORY_BEAN_NAME, queues = "zzuag.question.create")
    public void receiveEvent(SaveQuestionEvent event) {
        System.out.println("Received event: " + event);
        log.info("Received event: {}", event);
        publisher.publishEvent(event);
    }

    @RabbitListener(containerFactory = ZRMQListenerConfig.RABBIT_LISTENER_CONTAINER_FACTORY_BEAN_NAME, queues = "zzuag.question.delete")
    public void receiveEvent(DeleteQuestionEvent event) {
        log.info("Received event: {}", event);
        publisher.publishEvent(event);
    }
}
