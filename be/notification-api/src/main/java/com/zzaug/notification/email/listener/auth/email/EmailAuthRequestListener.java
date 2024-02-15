package com.zzaug.notification.email.listener.auth.email;

import com.rabbitmq.client.Channel;
import com.zzaug.notification.email.dto.AuthEmailDto;
import com.zzaug.notification.email.service.SendEmailAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailAuthRequestListener implements ChannelAwareMessageListener {

	private final EmailAuthRequestMapper emailAuthRequestMapper;
	private final SendEmailAuthService sendEmailAuthService;

	@Override
	public void onMessage(Message message, @Nullable Channel channel) throws Exception {
		AuthEmailDto dto = emailAuthRequestMapper.map(message);
		sendEmailAuthService.sendSimpleMessage(dto);
		channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
	}
}
