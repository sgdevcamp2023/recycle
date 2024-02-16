package com.zzaug.rabbitmq.config;

import static com.zzaug.rabbitmq.config.ZRabbiMQConfig.BEAN_NAME_PREFIX;

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

	public static final String RABBITMQ_PROPERTIES_BEAN_NAME = BEAN_NAME_PREFIX + "RabbitProperties";
	public static final String RABBIT_CONNECTION_FACTORY_CONFIGURER_BEAN_NAME =
			BEAN_NAME_PREFIX + "ConnectionFactoryConfigurer";
	public static final String RABBIT_TEMPLATE_BEAN_NAME = BEAN_NAME_PREFIX + "RabbitTemplate";
	public static final String AMQP_ADMIN_BEAN_NAME = BEAN_NAME_PREFIX + "AmqpAdmin";
	public static final String RABBIT_MESSAGING_TEMPLATE_BEAN_NAME =
			BEAN_NAME_PREFIX + "RabbitMessagingTemplate";

	@Bean(name = RABBITMQ_PROPERTIES_BEAN_NAME)
	@ConfigurationProperties(prefix = "spring.rabbitmq")
	RabbitProperties rabbitMQProperties() {
		return new RabbitProperties();
	}

	@Bean(name = BEAN_NAME_PREFIX + "ConnectionFactory")
	ConnectionFactory connectionFactory() {
		RabbitProperties properties = rabbitMQProperties();
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setHost(properties.getHost());
		connectionFactory.setPort(properties.getPort());
		connectionFactory.setUsername(properties.getUsername());
		connectionFactory.setPassword(properties.getPassword());
		return connectionFactory;
	}

	@Bean(name = RABBIT_CONNECTION_FACTORY_CONFIGURER_BEAN_NAME)
	@ConditionalOnMissingBean
	CachingConnectionFactoryConfigurer rabbitConnectionFactoryConfigurer(
			ObjectProvider<ConnectionNameStrategy> connectionNameStrategy) {
		CachingConnectionFactoryConfigurer configurer =
				new CachingConnectionFactoryConfigurer(rabbitMQProperties());
		configurer.setConnectionNameStrategy(connectionNameStrategy.getIfUnique());
		return configurer;
	}

	@Bean(name = RABBIT_TEMPLATE_BEAN_NAME)
	public RabbitTemplate rabbitTemplate(ObjectProvider<MessageConverter> messageConverter) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate();
		rabbitTemplate.setConnectionFactory(connectionFactory());
		rabbitTemplate.setMandatory(true);
		rabbitTemplate.setChannelTransacted(true);
		rabbitTemplate.setReplyTimeout(60000);
		rabbitTemplate.setMessageConverter(messageConverter.getIfUnique());
		return rabbitTemplate;
	}

	@Bean(name = AMQP_ADMIN_BEAN_NAME)
	public AmqpAdmin amqpAdmin(ConnectionFactory connectionFactory) {
		return new RabbitAdmin(connectionFactory);
	}

	@Bean(name = RABBIT_MESSAGING_TEMPLATE_BEAN_NAME)
	@ConditionalOnSingleCandidate(RabbitTemplate.class)
	public RabbitMessagingTemplate rabbitMessagingTemplate(RabbitTemplate rabbitTemplate) {
		return new RabbitMessagingTemplate(rabbitTemplate);
	}
}
