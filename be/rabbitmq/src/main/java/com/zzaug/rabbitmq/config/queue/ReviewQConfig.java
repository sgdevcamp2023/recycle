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

	private static final String WILD_CARD = ".#";

	@Bean
	TopicExchange reviewTopic() {
		return new TopicExchange(ZRMQProperties.REVIEW_TOPIC_NAME);
	}

	@Bean
	Queue reviewQueue() {
		return QueueBuilder.durable(ZRMQProperties.REVIEW_QUEUE_NAME).build();
	}

	@Bean
	Binding reviewQBinding() {
		return BindingBuilder.bind(reviewQueue())
				.to(reviewTopic())
				.with(ZRMQProperties.REVIEW_KEY_NAME + WILD_CARD);
	}
}
