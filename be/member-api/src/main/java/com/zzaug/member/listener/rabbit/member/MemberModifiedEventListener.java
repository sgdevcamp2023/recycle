package com.zzaug.member.listener.rabbit.member;

import com.rabbitmq.client.Channel;
import com.zzaug.member.listener.rabbit.MemberIdMapper;
import com.zzaug.security.redis.auth.WhiteAuthTokenHash;
import com.zzaug.security.redis.auth.WhiteAuthTokenHashRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MemberModifiedEventListener implements ChannelAwareMessageListener {

	private final MemberIdMapper memberIdMapper;
	private final WhiteAuthTokenHashRepository whiteAuthTokenHashRepository;

	@Override
	public void onMessage(Message message, @Nullable Channel channel) throws Exception {
		Long memberId = memberIdMapper.map(message);
		List<WhiteAuthTokenHash> memberIds = whiteAuthTokenHashRepository.findAllByMemberId(memberId);
		whiteAuthTokenHashRepository.deleteAll(memberIds);
		channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
	}
}
