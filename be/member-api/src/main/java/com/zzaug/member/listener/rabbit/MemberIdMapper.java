package com.zzaug.member.listener.rabbit;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberIdMapper {

	private static final String MEMBER_ID_KEY = "member_id";

	private final ObjectMapper objectMapper;

	public Long map(Message message) throws Exception {
		String bodyAsString = new String(message.getBody());
		Map<String, Object> map = objectMapper.readValue(bodyAsString, Map.class);
		return Long.parseLong(map.get(MEMBER_ID_KEY).toString());
	}
}
