package com.zzaug.rabbitmq.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ZRMQConnectionConfig {

	private final int MAX_TRY_COUNT = 3;
	private final int INITIAL_INTERVAL = 3000;
	private final int MULTIPLIER = 3;
	private final int MAX_INTERVAL = 10000;

	@Value("${spring.rabbitmq.host}")
	private String host;

	@Value("${spring.rabbitmq.username}")
	private String username;

	@Value("${spring.rabbitmq.password}")
	private String password;

	@Value("${spring.rabbitmq.port}")
	private int port;

	@Bean
	ConnectionFactory connectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setHost(host);
		connectionFactory.setPort(port);
		connectionFactory.setUsername(username);
		connectionFactory.setPassword(password);
		return connectionFactory;
	}

	@Bean
	MessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public RabbitTemplate amqpTemplate(
			ConnectionFactory connectionFactory, MessageConverter queueMessageConverter) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate();
		rabbitTemplate.setConnectionFactory(connectionFactory);
		rabbitTemplate.setMandatory(true);
		rabbitTemplate.setChannelTransacted(true);
		rabbitTemplate.setReplyTimeout(60000);
		rabbitTemplate.setMessageConverter(queueMessageConverter);
		return rabbitTemplate;
	}

	@Bean
	SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
			RabbitTemplate rabbitTemplate) {
		final SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory());
		factory.setDefaultRequeueRejected(false);
		factory.setMessageConverter(messageConverter());
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
