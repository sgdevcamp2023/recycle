package com.zzaug.rabbitmq.message;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class DeadLetterMessage implements Serializable {

	private final String queueName;
	private final String listenerName;
	private final String event;
	private final LocalDateTime deadTime;
}
