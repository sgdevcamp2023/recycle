package com.zzaug.notification.email.listener.auth.email;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzaug.notification.email.dto.AuthEmailDto;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailAuthRequestMapper {

	private static final String EMAIL_KEY = "email";
	private static final String CODE_KEY = "code";

	private final ObjectMapper objectMapper;

	public AuthEmailDto map(Message message) throws Exception {
		String bodyAsString = new String(message.getBody());
		Map<String, Object> map = objectMapper.readValue(bodyAsString, Map.class);
		return AuthEmailDto.builder()
				.to((String) map.get(EMAIL_KEY))
				.code((String) map.get(CODE_KEY))
				.build();
	}
}
