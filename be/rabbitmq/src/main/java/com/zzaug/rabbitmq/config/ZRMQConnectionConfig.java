package com.zzaug.rabbitmq.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionNameStrategy;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.amqp.CachingConnectionFactoryConfigurer;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ZRMQConnectionConfig {

	@Bean
	@ConfigurationProperties(prefix = "spring.rabbitmq")
	RabbitProperties rabbitMQProperties() {
		return new RabbitProperties();
	}

	@Bean
	ConnectionFactory connectionFactory() {
		RabbitProperties properties = rabbitMQProperties();
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setHost(properties.getHost());
		connectionFactory.setPort(properties.getPort());
		connectionFactory.setUsername(properties.getUsername());
		connectionFactory.setPassword(properties.getPassword());
		return connectionFactory;
	}

	@Bean
	@ConditionalOnMissingBean
	CachingConnectionFactoryConfigurer rabbitConnectionFactoryConfigurer(
			RabbitProperties rabbitProperties,
			ObjectProvider<ConnectionNameStrategy> connectionNameStrategy) {
		CachingConnectionFactoryConfigurer configurer =
				new CachingConnectionFactoryConfigurer(rabbitProperties);
		configurer.setConnectionNameStrategy(connectionNameStrategy.getIfUnique());
		return configurer;
	}

	@Bean
	public RabbitTemplate rabbitTemplate(ObjectProvider<MessageConverter> messageConverter) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate();
		rabbitTemplate.setConnectionFactory(connectionFactory());
		rabbitTemplate.setMandatory(true);
		rabbitTemplate.setChannelTransacted(true);
		rabbitTemplate.setReplyTimeout(60000);
		rabbitTemplate.setMessageConverter(messageConverter.getIfUnique());
		return rabbitTemplate;
	}

	@Bean
	public AmqpAdmin amqpAdmin(ConnectionFactory connectionFactory) {
		return new RabbitAdmin(connectionFactory);
	}

	@Bean
	@ConditionalOnSingleCandidate(RabbitTemplate.class)
	public RabbitMessagingTemplate rabbitMessagingTemplate(RabbitTemplate rabbitTemplate) {
		return new RabbitMessagingTemplate(rabbitTemplate);
	}
}
