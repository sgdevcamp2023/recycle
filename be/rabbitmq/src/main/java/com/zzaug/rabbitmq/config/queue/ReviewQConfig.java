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
public class ReviewQConfig {

	private static final String X_DEAD_LETTER_EXCHANGE_KEY = "x-dead-letter-exchange";
	private static final String X_DEAD_LETTER_ROUTING_KEY = "x-dead-letter-routing-key";
	private static final String X_MESSAGE_TTL_KEY = "x-message-ttl";
	private static final Long X_MESSAGE_TTL = 1000 * 60 * 30L;
	private static final String WILD_CARD = ".#";

	@Bean
	TopicExchange reviewTopic() {
		return new TopicExchange(ZRMQProperties.REVIEW_TOPIC_NAME);
	}

	@Bean
	Queue reviewDeleteQueue() {
		return QueueBuilder.durable(ZRMQProperties.REVIEW_DELETE_QUEUE_NAME)
				.withArgument(X_DEAD_LETTER_EXCHANGE_KEY, ZRMQProperties.DEAD_LETTER_EXCHANGE_NAME)
				.withArgument(X_MESSAGE_TTL_KEY, X_MESSAGE_TTL)
				.withArgument(X_DEAD_LETTER_ROUTING_KEY, ZRMQProperties.DEAD_LETTER_KEY_NAME + ".review")
				.build();
	}

	@Bean
	Binding reviewDeleteQBinding() {
		return BindingBuilder.bind(reviewDeleteQueue())
				.to(reviewTopic())
				.with(ZRMQProperties.REVIEW_DELETE_KEY_NAME + WILD_CARD);
	}

	@Bean
	Queue reviewCreateQueue() {
		return QueueBuilder.durable(ZRMQProperties.REVIEW_CREATE_QUEUE_NAME)
				.withArgument(X_DEAD_LETTER_EXCHANGE_KEY, ZRMQProperties.DEAD_LETTER_EXCHANGE_NAME)
				.withArgument(X_MESSAGE_TTL_KEY, X_MESSAGE_TTL)
				.withArgument(X_DEAD_LETTER_ROUTING_KEY, ZRMQProperties.DEAD_LETTER_KEY_NAME + ".review")
				.build();
	}

	@Bean
	Binding reviewCreateQBinding() {
		return BindingBuilder.bind(reviewCreateQueue())
				.to(reviewTopic())
				.with(ZRMQProperties.REVIEW_CREATE_KEY_NAME + WILD_CARD);
	}

	@Bean
	Queue reviewUpdateQueue() {
		return QueueBuilder.durable(ZRMQProperties.REVIEW_UPDATE_QUEUE_NAME)
				.withArgument(X_DEAD_LETTER_EXCHANGE_KEY, ZRMQProperties.DEAD_LETTER_EXCHANGE_NAME)
				.withArgument(X_MESSAGE_TTL_KEY, X_MESSAGE_TTL)
				.withArgument(X_DEAD_LETTER_ROUTING_KEY, ZRMQProperties.DEAD_LETTER_KEY_NAME + ".review")
				.build();
	}

	@Bean
	Binding reviewUpdateQBinding() {
		return BindingBuilder.bind(reviewCreateQueue())
				.to(reviewTopic())
				.with(ZRMQProperties.REVIEW_UPDATE_KEY_NAME + WILD_CARD);
	}
}
