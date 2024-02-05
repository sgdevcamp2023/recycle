package com.zzaug.rabbitmq.config.queue;

import com.zzaug.rabbitmq.config.ZRMQProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class DeadLetterQConfig {

	@Bean
	DirectExchange deadLetterExchange() {
		return ExchangeBuilder.directExchange(ZRMQProperties.DEAD_LETTER_EXCHANGE_NAME).build();
	}

	@Bean
	Queue memberDeadLetterQueue() {
		return QueueBuilder.durable(ZRMQProperties.DEAD_LETTER_QUEUE_NAME + ".member").build();
	}

	@Bean
	Binding memberDeadLetterBinding() {
		return BindingBuilder.bind(memberDeadLetterQueue())
				.to(deadLetterExchange())
				.with(ZRMQProperties.DEAD_LETTER_KEY_NAME + ".member");
	}

	@Bean
	Queue reviewDeadLetterQueue() {
		return QueueBuilder.durable(ZRMQProperties.DEAD_LETTER_QUEUE_NAME + ".review").build();
	}

	@Bean
	Binding reviewDeadLetterBinding() {
		return BindingBuilder.bind(memberDeadLetterQueue())
				.to(deadLetterExchange())
				.with(ZRMQProperties.DEAD_LETTER_KEY_NAME + ".review");
	}
}
