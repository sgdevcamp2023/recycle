package com.zzaug.rabbitmq.listener.dead;

import com.zzaug.rabbitmq.config.ZRMQProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DeadLetterListenerConfig {

	private final DeadLetterListener deadLetterListener;

	@Bean
	SimpleMessageListenerContainer deadLetterListenerContainer(
			SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory) {
		SimpleMessageListenerContainer container =
				rabbitListenerContainerFactory.createListenerContainer();
		container.setQueueNames(ZRMQProperties.DEAD_LETTER_QUEUE_NAME);
		container.setMessageListener(deadLetterListener);
		return container;
	}
}
