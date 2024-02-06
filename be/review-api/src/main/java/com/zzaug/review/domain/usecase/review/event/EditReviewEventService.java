package com.zzaug.review.domain.usecase.review.event;

import com.zzaug.review.domain.event.review.EditReviewEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class EditReviewEventService {
    @Value("${spring.rabbitmq.exchange}")
    private String exchangeName;

    @Value("${spring.rabbitmq.routing-key}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;

    @EventListener
    public void sendEvent(EditReviewEvent event) {
        rabbitTemplate.convertAndSend(exchangeName, routingKey, event);
        log.info("Sending event: {}", event);
    }
}
