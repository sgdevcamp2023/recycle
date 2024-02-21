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
public class QuestionQConfig {

    private static final String X_DEAD_LETTER_EXCHANGE_KEY = "x-dead-letter-exchange";
    private static final String X_DEAD_LETTER_ROUTING_KEY = "x-dead-letter-routing-key";
    private static final String X_MESSAGE_TTL_KEY = "x-message-ttl";
    private static final Long X_MESSAGE_TTL = 1000 * 60 * 30L;
    private static final String WILD_CARD = ".#";

    @Bean
    TopicExchange questionTopic() {
        return new TopicExchange(ZRMQProperties.QUESTION_TOPIC_NAME);
    }

    @Bean
    Queue questionDeleteQueue() {
        return QueueBuilder.durable(ZRMQProperties.QUESTION_DELETE_QUEUE_NAME)
                .withArgument(X_DEAD_LETTER_EXCHANGE_KEY, ZRMQProperties.DEAD_LETTER_EXCHANGE_NAME)
                .withArgument(X_MESSAGE_TTL_KEY, X_MESSAGE_TTL)
                .withArgument(X_DEAD_LETTER_ROUTING_KEY, ZRMQProperties.DEAD_LETTER_KEY_NAME + ".review")
                .build();
    }

    @Bean
    Binding questionDeleteQBinding() {
        return BindingBuilder.bind(questionDeleteQueue())
                .to(questionTopic())
                .with(ZRMQProperties.QUESTION_DELETE_KEY_NAME + WILD_CARD);
    }

    @Bean
    Queue questioonCreateQueue() {
        return QueueBuilder.durable(ZRMQProperties.QUESTION_CREATE_QUEUE_NAME)
                .withArgument(X_DEAD_LETTER_EXCHANGE_KEY, ZRMQProperties.DEAD_LETTER_EXCHANGE_NAME)
                .withArgument(X_MESSAGE_TTL_KEY, X_MESSAGE_TTL)
                .withArgument(X_DEAD_LETTER_ROUTING_KEY, ZRMQProperties.DEAD_LETTER_KEY_NAME + ".review")
                .build();
    }

    @Bean
    Binding questionCreateQBinding() {
        return BindingBuilder.bind(questioonCreateQueue())
                .to(questionTopic())
                .with(ZRMQProperties.QUESTION_CREATE_KEY_NAME + WILD_CARD);
    }
}