package com.zzaug.rabbitmq.config.queue;

import com.zzaug.rabbitmq.config.ZRMQProperties;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MemberQConfig {

	private static final String X_DEAD_LETTER_EXCHANGE_KEY = "x-dead-letter-exchange";
	private static final String X_DEAD_LETTER_ROUTING_KEY = "x-dead-letter-routing-key";
	private static final String X_MESSAGE_TTL_KEY = "x-message-ttl";
	private static final Long X_MESSAGE_TTL = 1000 * 60 * 30L;

	private static final String WILD_CARD = ".#";

	@Bean
	TopicExchange memberTopic() {
		return new TopicExchange(ZRMQProperties.MEMBER_TOPIC_NAME);
	}

	@Bean
	Queue memberUpdatedQueue() {
		return QueueBuilder.durable(ZRMQProperties.MEMBER_UPDATE_QUEUE_NAME)
				.withArgument(X_DEAD_LETTER_EXCHANGE_KEY, ZRMQProperties.DEAD_LETTER_EXCHANGE_NAME)
				.withArgument(X_DEAD_LETTER_ROUTING_KEY, ZRMQProperties.DEAD_LETTER_KEY_NAME + ".member")
				.withArgument(X_MESSAGE_TTL_KEY, X_MESSAGE_TTL)
				.build();
	}

	@Bean
	Binding memberUpdatedQBinding() {
		return BindingBuilder.bind(memberUpdatedQueue())
				.to(memberTopic())
				.with(ZRMQProperties.MEMBER_UPDATED_KEY_NAME + WILD_CARD);
	}

	@Bean
	Queue memberStatusQueue() {
		return QueueBuilder.durable(ZRMQProperties.MEMBER_STATUS_QUEUE_NAME)
				.withArgument(X_DEAD_LETTER_EXCHANGE_KEY, ZRMQProperties.DEAD_LETTER_EXCHANGE_NAME)
				.withArgument(X_DEAD_LETTER_ROUTING_KEY, ZRMQProperties.DEAD_LETTER_KEY_NAME + ".member")
				.withArgument(X_MESSAGE_TTL_KEY, X_MESSAGE_TTL)
				.build();
	}

	@Bean
	Binding memberStatusQBinding() {
		return BindingBuilder.bind(memberStatusQueue())
				.to(memberTopic())
				.with(ZRMQProperties.MEMBER_STATUS_KEY_NAME + WILD_CARD);
	}
}
