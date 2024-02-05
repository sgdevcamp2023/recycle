package com.zzaug.rabbitmq.config;

public interface ZRMQProperties {

	String DEAD_LETTER_EXCHANGE_NAME = "dead.zzaug";
	String DEAD_LETTER_KEY_NAME = "dead.zzaug";
	String DEAD_LETTER_QUEUE_NAME = "dead.zzuag";

	String MEMBER_KEY_NAME = "zzuag.member";
	String MEMBER_QUEUE_NAME = "zzuag.member";
	String MEMBER_TOPIC_NAME = "topic.zzuag.member";

	String REVIEW_KEY_NAME = "zzuag.review";
	String REVIEW_QUEUE_NAME = "zzuag.review";
	String REVIEW_TOPIC_NAME = "topic.zzuag.review";
}
