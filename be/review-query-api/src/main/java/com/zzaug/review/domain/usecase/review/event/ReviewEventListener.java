package com.zzaug.review.domain.usecase.review.event;

import com.zzaug.rabbitmq.config.ZRMQListenerConfig;
import com.zzaug.review.domain.event.review.DeleteReviewEvent;
import com.zzaug.review.domain.event.review.EditReviewEvent;
import com.zzaug.review.domain.event.review.SaveReviewEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewEventListener {

    private final ApplicationEventPublisher publisher;

    @RabbitListener(containerFactory = ZRMQListenerConfig.RABBIT_LISTENER_CONTAINER_FACTORY_BEAN_NAME, queues = "zzuag.review.create")
    public void receiveEvent(SaveReviewEvent event) {
        log.info("Received event: {}", event);
        publisher.publishEvent(event);
    }

    @RabbitListener(containerFactory = ZRMQListenerConfig.RABBIT_LISTENER_CONTAINER_FACTORY_BEAN_NAME, queues = "zzuag.review.update")
    public void receiveEvent(EditReviewEvent event) {
        log.info("Received event: {}", event);
        publisher.publishEvent(event);
    }

    @RabbitListener(containerFactory = ZRMQListenerConfig.RABBIT_LISTENER_CONTAINER_FACTORY_BEAN_NAME, queues = "zzuag.review.delete")
    public void receiveEvent(DeleteReviewEvent event) {
        log.info("Received event: {}", event);
        publisher.publishEvent(event);
    }
}
