package com.zzaug.rabbitmq.listener.dead;

import com.zzaug.rabbitmq.config.ZRMQProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ReviewDeadLetterListenerConfig {

	private final int CONCURRENT_CONSUMERS_COUNT = 1;
	private final int PREFETCH_COUNT = AbstractMessageListenerContainer.DEFAULT_PREFETCH_COUNT;

	private final BasicDeadLetterListener reviewBasicDeadLetterListener;

	@Bean
	SimpleMessageListenerContainer reviewDeadLetterListenerContainer(
			SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory) {
		SimpleMessageListenerContainer container =
				rabbitListenerContainerFactory.createListenerContainer();
		container.setQueueNames(ZRMQProperties.DEAD_LETTER_QUEUE_NAME + ".review");
		container.setMessageListener(reviewBasicDeadLetterListener);
		container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
		container.setConcurrentConsumers(CONCURRENT_CONSUMERS_COUNT);
		container.setPrefetchCount(PREFETCH_COUNT);
		return container;
	}
}
