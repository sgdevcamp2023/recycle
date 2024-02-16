package com.zzaug.member.domain.usecase.member;

import static com.zzaug.member.config.MemberSessionConfig.SESSION_NAME_SPACE;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.session.data.redis.RedisIndexedSessionRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ActiveMemberUseCase {

	private static final String MEMBER_ID_SESSION_KEY = "sessionAttr:memberId";
	private final RedisIndexedSessionRepository redisIndexedSessionRepository;

	public Set<Long> execute() {
		Set<Long> memberIds = new HashSet<>();
		Set<Object> keys = getSessionsKeys();
		for (Object key : keys) {
			Long memberId =
					(Long)
							redisIndexedSessionRepository
									.getSessionRedisOperations()
									.opsForHash()
									.get(key, MEMBER_ID_SESSION_KEY);
			memberIds.add(memberId);
		}
		return memberIds;
	}

	private Set<Object> getSessionsKeys() {
		Set<Object> keys =
				redisIndexedSessionRepository
						.getSessionRedisOperations()
						.opsForList()
						.getOperations()
						.keys(SESSION_NAME_SPACE + ":sessions:*");
		assert keys != null;
		return keys.stream()
				.filter(key -> !key.toString().contains("expires"))
				.collect(Collectors.toSet());
	}
}
