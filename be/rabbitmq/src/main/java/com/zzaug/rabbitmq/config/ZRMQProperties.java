package com.zzaug.rabbitmq.config;

public interface ZRMQProperties {

	String DEAD_LETTER_EXCHANGE_NAME = "dead-letter-exchange";
	String DEAD_LETTER_KEY_NAME = "dead-letter-queue";
	String DEAD_LETTER_QUEUE_NAME = "dead-letter-queue";

	String MEMBER_KEY_NAME = "zzuag.member";
	String MEMBER_QUEUE_NAME = "zzuag.member";
	String MEMBER_TOPIC_NAME = "topic.zzuag.member";

	String REVIEW_KEY_NAME = "zzuag.review";
	String REVIEW_QUEUE_NAME = "zzuag.review";
	String REVIEW_TOPIC_NAME = "topic.zzuag.review";
}
