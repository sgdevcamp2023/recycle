package com.zzaug.rabbitmq.listener.dead;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeadLetterMapper {

	private final ObjectMapper objectMapper;

	public DeadLetterMessage map(byte[] body) throws Exception {
		return objectMapper.readValue(body, DeadLetterMessage.class);
	}
}
