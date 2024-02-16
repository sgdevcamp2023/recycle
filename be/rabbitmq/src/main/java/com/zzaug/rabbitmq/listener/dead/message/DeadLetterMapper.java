package com.zzaug.rabbitmq.listener.dead.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Date;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeadLetterMapper {

	private static final String REASON_KEY = "reason";
	private static final String COUNT_KEY = "count";
	private static final String EXCHANGE_KEY = "exchange";
	private static final String TIME_KEY = "time";
	private static final String ROUTING_KEYS = "routing-keys";
	private static final String QUEUE_KEY = "queue";

	private final ObjectMapper objectMapper;

	public DeadLetterMessage map(Message message) throws Exception {
		String body = objectMapper.readValue(message.getBody(), String.class);
		List<Map<String, ?>> xDeathHeaders = message.getMessageProperties().getXDeathHeader();
		Map<String, ?> xDeathHeader = xDeathHeaders.get(0);
		return DeadLetterMessage.builder()
				.reason((String) xDeathHeader.get(REASON_KEY))
				.count((Long) xDeathHeader.get(COUNT_KEY))
				.exchange((String) xDeathHeader.get(EXCHANGE_KEY))
				.time((Date) xDeathHeader.get(TIME_KEY))
				.routingKeys((List<String>) xDeathHeader.get(ROUTING_KEYS))
				.queue((String) xDeathHeader.get(QUEUE_KEY))
				.message(body)
				.build();
	}
}
