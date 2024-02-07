package com.zzaug.rabbitmq.listener.dead.message;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
public class DeadLetterMessage implements Serializable {

	private String reason;
	private Long count;
	private String exchange;
	private Date time;
	private List<String> routingKeys;
	private String queue;
	private String message;
}
