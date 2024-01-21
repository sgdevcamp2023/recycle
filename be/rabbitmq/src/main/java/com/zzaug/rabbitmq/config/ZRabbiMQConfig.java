package com.zzaug.rabbitmq.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = ZRabbiMQConfig.BASE_PACKAGE)
@EnableAutoConfiguration(exclude = RabbitAutoConfiguration.class)
public class ZRabbiMQConfig {

	public static final String BASE_PACKAGE = "com.zzaug.rabbitmq";
	public static final String SERVICE_NAME = "recycle";
	public static final String MODULE_NAME = "rabbitmq";
	public static final String BEAN_NAME_PREFIX = "RabbitMQ";
	public static final String PROPERTY_PREFIX = SERVICE_NAME + "." + MODULE_NAME;

	@Bean
	ObjectMapper objectMapper() {
		return new ObjectMapper();
	}

	@Bean
	MessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter();
	}
}
