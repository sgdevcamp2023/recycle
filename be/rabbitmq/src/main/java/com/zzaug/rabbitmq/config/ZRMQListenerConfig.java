package com.zzaug.rabbitmq.config;

import com.zzaug.rabbitmq.listener.ZRMQExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@RequiredArgsConstructor
@Import({ZRMQConnectionConfig.class})
public class ZRMQListenerConfig {

	private final int MAX_TRY_COUNT = 3;
	private final int INITIAL_INTERVAL = 3000;
	private final int MULTIPLIER = 3;
	private final int MAX_INTERVAL = 10000;

	private final ConnectionFactory connectionFactory;
	private final MessageConverter messageConverter;
	private final RabbitTemplate rabbitTemplate;

	@Bean
	SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
		final SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory);
		factory.setDefaultRequeueRejected(false);
		factory.setMessageConverter(messageConverter);
		factory.setChannelTransacted(true);
		factory.setAdviceChain(
				RetryInterceptorBuilder.stateless()
						.maxAttempts(MAX_TRY_COUNT)
						.recoverer(new ZRMQExceptionHandler(rabbitTemplate))
						.backOffOptions(INITIAL_INTERVAL, MULTIPLIER, MAX_INTERVAL)
						.build());
		return factory;
	}
}
