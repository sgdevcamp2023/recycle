package com.zzaug.rabbitmq.listener.dead;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeadLetterListener implements ChannelAwareMessageListener {

	private final DeadLetterMapper deadLetterMapper;

	@Override
	public void onMessage(Message message, @Nullable Channel channel) throws Exception {
		log.info("Received Dead Letter Message : {}", message);
		byte[] body = message.getBody();
		DeadLetterMessage deadLetterMessage = deadLetterMapper.map(body);
		log.info("Dead Letter Message : {}", deadLetterMessage);
	}
}
