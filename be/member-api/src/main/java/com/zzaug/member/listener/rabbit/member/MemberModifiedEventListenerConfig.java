package com.zzaug.member.listener.rabbit.member;

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
public class MemberModifiedEventListenerConfig {

	private final int CONCURRENT_CONSUMERS_COUNT = 1;
	private final int PREFETCH_COUNT = AbstractMessageListenerContainer.DEFAULT_PREFETCH_COUNT;

	private final MemberModifiedEventListener memberModifiedEventListener;

	@Bean
	SimpleMessageListenerContainer MemberModifiedEventListenerContainer(
			SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory) {
		SimpleMessageListenerContainer container =
				rabbitListenerContainerFactory.createListenerContainer();
		container.setQueueNames(
				ZRMQProperties.MEMBER_UPDATE_QUEUE_NAME, ZRMQProperties.MEMBER_STATUS_QUEUE_NAME);
		container.setMessageListener(memberModifiedEventListener);
		container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
		container.setConcurrentConsumers(CONCURRENT_CONSUMERS_COUNT);
		container.setPrefetchCount(PREFETCH_COUNT);
		return container;
	}
}
