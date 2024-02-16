package com.zzaug.member.domain.eventlistener;

import com.zzaug.member.domain.event.LogOutEvent;
import com.zzaug.member.domain.event.LoginEvent;
import com.zzaug.rabbitmq.config.ZRMQProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MemberStatusChangedEventListener {

	private final RabbitTemplate rabbitTemplate;

	@EventListener
	public void onMessage(LoginEvent message) {
		rabbitTemplate.convertAndSend(
				ZRMQProperties.MEMBER_TOPIC_NAME,
				ZRMQProperties.MEMBER_STATUS_KEY_NAME + ".login",
				message);
	}

	@EventListener
	public void onMessage(LogOutEvent message) {
		rabbitTemplate.convertAndSend(
				ZRMQProperties.MEMBER_TOPIC_NAME,
				ZRMQProperties.MEMBER_STATUS_KEY_NAME + ".logout",
				message);
	}
}
