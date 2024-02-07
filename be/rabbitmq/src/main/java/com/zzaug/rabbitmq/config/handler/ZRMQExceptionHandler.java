package com.zzaug.rabbitmq.config.handler;

import com.zzaug.rabbitmq.config.ZRMQProperties;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.RejectAndDontRequeueRecoverer;

/**
 * ZRMQExceptionHandler
 *
 * <p>Listener에서 대비하지 못한 예외가 발생했을 때, 해당 메시지를 Dead Letter Queue로 보내는 역할을 한다.
 */
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
		// Dead Letter Queue로 보낸다.
		rabbitTemplate.send(
				ZRMQProperties.DEAD_LETTER_EXCHANGE_NAME, ZRMQProperties.DEAD_LETTER_KEY_NAME, message);
	}
}
