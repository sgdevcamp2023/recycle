package com.zzaug.review.domain.usecase.question.event;

import com.zzaug.review.domain.event.question.DeleteQuestionEvent;
import com.zzaug.review.domain.event.question.SaveQuestionEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class QuestionEventService {
	@Value("${spring.rabbitmq.question-exchange}")
	private String exchangeName;

	@Value("${spring.rabbitmq.question-routing-key}")
	private String routingKey;

	private final RabbitTemplate rabbitTemplate;

	@EventListener
	public void sendEvent(SaveQuestionEvent event) {
		log.debug("Event received: {}", event);
		System.out.println("Event received: " + event);
		rabbitTemplate.convertAndSend(exchangeName, routingKey + ".create", event);
		log.debug("Event sent: {}", event);
	}

	@EventListener
	public void sendEvent(DeleteQuestionEvent event) {
		log.debug("Event received: {}", event);
		rabbitTemplate.convertAndSend(exchangeName, routingKey + ".delete", event);
		log.debug("Event sent: {}", event);
	}
}
