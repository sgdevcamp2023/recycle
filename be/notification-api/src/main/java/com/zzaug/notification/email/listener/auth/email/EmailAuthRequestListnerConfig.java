package com.zzaug.notification.email.listener.auth.email;

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
public class EmailAuthRequestListnerConfig {

	private final int CONCURRENT_CONSUMERS_COUNT = 1;
	private final int PREFETCH_COUNT = AbstractMessageListenerContainer.DEFAULT_PREFETCH_COUNT;

	private final EmailAuthRequestListener emailAuthRequestListener;

	@Bean
	SimpleMessageListenerContainer notificationEmailListenerContainer(
			SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory) {
		SimpleMessageListenerContainer container =
				rabbitListenerContainerFactory.createListenerContainer();
		container.setQueueNames(ZRMQProperties.NOTIFICATION_EMAIL_QUEUE_NAME);
		container.setMessageListener(emailAuthRequestListener);
		container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
		container.setConcurrentConsumers(CONCURRENT_CONSUMERS_COUNT);
		container.setPrefetchCount(PREFETCH_COUNT);
		return container;
	}
}
