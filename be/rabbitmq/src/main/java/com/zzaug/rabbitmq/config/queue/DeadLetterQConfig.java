package com.zzaug.rabbitmq.config.queue;

import com.zzaug.rabbitmq.config.ZRMQProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class DeadLetterQConfig {

	@Bean
	FanoutExchange deadLetterNotifyExchange() {
		return ExchangeBuilder.fanoutExchange(ZRMQProperties.DEAD_LETTER_EXCHANGE_NAME).build();
	}

	@Bean
	Queue deadLetterNotifyQueue() {
		return QueueBuilder.durable(ZRMQProperties.DEAD_LETTER_QUEUE_NAME).build();
	}

	@Bean
	Binding deadLetterNotifyBinding() {
		return BindingBuilder.bind(deadLetterNotifyQueue()).to(deadLetterNotifyExchange());
	}
}
