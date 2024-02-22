package com.zzaug.review.domain.usecase.review.event;

import com.zzaug.review.domain.event.review.DeleteReviewEvent;
import com.zzaug.review.domain.event.review.EditReviewEvent;
import com.zzaug.review.domain.event.review.SaveReviewEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReviewEventService {
	@Value("${spring.rabbitmq.review-exchange}")
	private String exchangeName;

	@Value("${spring.rabbitmq.review-routing-key}")
	private String routingKey;

	private final RabbitTemplate rabbitTemplate;

	@EventListener
	public void sendEvent(SaveReviewEvent event) {
		rabbitTemplate.convertAndSend(exchangeName, routingKey + ".create", event);
		log.info("Sending event: {}", event);
	}

	@EventListener
	public void sendEvent(EditReviewEvent event) {
		rabbitTemplate.convertAndSend(exchangeName, routingKey + ".update", event);
		log.info("Sending event: {}", event);
	}

	@EventListener
	public void sendEvent(DeleteReviewEvent event) {
		rabbitTemplate.convertAndSend(exchangeName, routingKey + ".delete", event);
		log.info("Sending event: {}", event);
	}
}
