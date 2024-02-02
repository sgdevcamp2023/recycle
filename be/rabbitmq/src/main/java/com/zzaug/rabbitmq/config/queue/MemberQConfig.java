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

	private static final String WILD_CARD = ".#";

	@Bean
	Queue memberQueue() {
		return QueueBuilder.durable(ZRMQProperties.MEMBER_QUEUE_NAME).build();
	}

	@Bean
	TopicExchange memberTopic() {
		return new TopicExchange(ZRMQProperties.MEMBER_TOPIC_NAME);
	}

	@Bean
	Binding memberQBinding() {
		return BindingBuilder.bind(memberQueue())
				.to(memberTopic())
				.with(ZRMQProperties.MEMBER_KEY_NAME + WILD_CARD);
	}
}
