package com.zzaug.member.redis.email;

import java.util.concurrent.TimeUnit;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash(value = "emailSession")
@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EmailAuthSession {

	@Id private String id;
	private Long memberId;
	private Long emailAuthId;

	@Builder.Default
	@TimeToLive(unit = TimeUnit.MINUTES)
	private Long ttl = 5L;

	@Indexed private String sessionId;
}
