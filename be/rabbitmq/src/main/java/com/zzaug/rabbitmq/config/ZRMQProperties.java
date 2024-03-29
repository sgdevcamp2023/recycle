package com.zzaug.rabbitmq.config;

public interface ZRMQProperties {

	String DEAD_LETTER_EXCHANGE_NAME = "dead.zzaug";
	String DEAD_LETTER_KEY_NAME = "dead.zzaug";
	String DEAD_LETTER_QUEUE_NAME = "dead.zzuag";

	String MEMBER_TOPIC_NAME = "topic.zzuag.member";
	String MEMBER_KEY_NAME = "zzuag.member";
	String MEMBER_STATUS_KEY_NAME = MEMBER_KEY_NAME + ".status";
	String MEMBER_UPDATED_KEY_NAME = MEMBER_KEY_NAME + ".updated";
	String MEMBER_QUEUE_NAME = "zzuag.member";
	String MEMBER_STATUS_QUEUE_NAME = MEMBER_QUEUE_NAME + ".status";
	String MEMBER_UPDATE_QUEUE_NAME = MEMBER_QUEUE_NAME + ".update";

	String REVIEW_TOPIC_NAME = "topic.zzuag.review";
	String REVIEW_KEY_NAME = "zzuag.review";
	String REVIEW_CREATE_KEY_NAME = REVIEW_KEY_NAME + ".create";
	String REVIEW_DELETE_KEY_NAME = REVIEW_KEY_NAME + ".delete";
	String REVIEW_UPDATE_KEY_NAME = REVIEW_KEY_NAME + ".update";
	String REVIEW_QUEUE_NAME = "zzuag.review";
	String REVIEW_CREATE_QUEUE_NAME = REVIEW_QUEUE_NAME + ".create";
	String REVIEW_DELETE_QUEUE_NAME = REVIEW_QUEUE_NAME + ".delete";
	String REVIEW_UPDATE_QUEUE_NAME = REVIEW_QUEUE_NAME + ".update";

	String QUESTION_TOPIC_NAME = "topic.zzuag.question";
	String QUESTION_KEY_NAME = "zzuag.question";
	String QUESTION_CREATE_KEY_NAME = QUESTION_KEY_NAME + ".create";
	String QUESTION_DELETE_KEY_NAME = QUESTION_KEY_NAME + ".delete";
	String QUESTION_QUEUE_NAME = "zzuag.question";
	String QUESTION_CREATE_QUEUE_NAME = QUESTION_QUEUE_NAME + ".create";
	String QUESTION_DELETE_QUEUE_NAME = QUESTION_QUEUE_NAME + ".delete";

	String NOTIFICATION_TOPIC_NAME = "topic.zzuag.notification";
	String NOTIFICATION_KEY_NAME = "zzuag.notification";
	String NOTIFICATION_QUEUE_NAME = "zzuag.notification";
	String NOTIFICATION_EMAIL_QUEUE_NAME = NOTIFICATION_QUEUE_NAME + ".email";
}
