package com.zzaug.rabbitmq.listener.dead;

import com.rabbitmq.client.Channel;
import com.zzaug.rabbitmq.listener.dead.message.DeadLetterMapper;
import com.zzaug.rabbitmq.listener.dead.message.DeadLetterMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BasicDeadLetterListener implements ChannelAwareMessageListener {

	private final DeadLetterMapper deadLetterMapper;

	@Override
	public void onMessage(Message message, @Nullable Channel channel) throws Exception {
		DeadLetterMessage deadLetterMessage = deadLetterMapper.map(message);
		log.info(
				"[{}] Dead Letter Message : {}",
				message.getMessageProperties().getConsumerTag(),
				deadLetterMessage);
		channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
	}
}
