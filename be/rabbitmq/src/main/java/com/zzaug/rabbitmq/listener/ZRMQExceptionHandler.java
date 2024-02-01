package com.zzaug.rabbitmq.listener;

import com.zzaug.rabbitmq.config.ZRMQProperties;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.RejectAndDontRequeueRecoverer;

@Slf4j
@RequiredArgsConstructor
public class ZRMQExceptionHandler extends RejectAndDontRequeueRecoverer {

	private final RabbitTemplate rabbitTemplate;

	@Override
	public void recover(Message message, Throwable cause) {
		final byte[] body = message.getBody();
		final String msg = new String(body, StandardCharsets.UTF_8);
		log.error("===================");
		log.debug(msg);
		log.warn("Retries exhausted for message " + message, cause);
		log.error("===================");
		log.debug("Send to Dead Letter Queue");
		rabbitTemplate.convertAndSend(
				ZRMQProperties.DEAD_LETTER_EXCHANGE_NAME, ZRMQProperties.DEAD_LETTER_KEY_NAME, msg);
	}
}
