package com.zzaug.rabbitmq.listener.dead;

import com.rabbitmq.client.Channel;
import com.zzaug.rabbitmq.listener.dead.message.DeadLetterMapper;
import com.zzaug.rabbitmq.listener.dead.message.DeadLetterMessage;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareBatchMessageListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BasicBatchDeadLetterListener implements ChannelAwareBatchMessageListener {

	private final DeadLetterMapper deadLetterMapper;

	@Override
	public void onMessageBatch(List<Message> messages, Channel channel) {
		log.info(">>>>>>>>>>>>>>>>>>>>>>>> Batch Dead Letter Listener");
		for (Message message : messages) {
			DeadLetterMessage deadLetterMessage = null;
			try {
				deadLetterMessage = deadLetterMapper.map(message);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			log.info(
					"[{}] Dead Letter Message : {}",
					message.getMessageProperties().getConsumerTag(),
					deadLetterMessage);
		}
		try {
			// 모든 메시지에 대해 한번에 Ack를 보낸다.
			channel.basicAck(
					messages.get(messages.size() - 1).getMessageProperties().getDeliveryTag(), true);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	}
}
