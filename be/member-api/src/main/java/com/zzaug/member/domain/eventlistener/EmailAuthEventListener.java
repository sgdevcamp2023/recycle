package com.zzaug.member.domain.eventlistener;

import com.zzaug.member.domain.message.EmailAuthMessage;
import com.zzaug.rabbitmq.config.ZRMQProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailAuthEventListener {

	private final RabbitTemplate rabbitTemplate;

	@EventListener
	public void onMessage(EmailAuthMessage message) {
		rabbitTemplate.convertAndSend(
				ZRMQProperties.NOTIFICATION_TOPIC_NAME,
				ZRMQProperties.NOTIFICATION_KEY_NAME + ".email",
				message);
	}
}
