package com.zzaug.rabbitmq.listener;

import com.zzaug.rabbitmq.config.ZRMQProperties;
import com.zzaug.rabbitmq.message.DeadLetterMessage;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Getter
@Component
public class DeadLetterListener {

	@RabbitListener(queues = ZRMQProperties.DEAD_LETTER_QUEUE_NAME)
	public void receiveMessage(DeadLetterMessage message) {
		log.info("Received Dead Letter Message : {}", message);
	}
}
