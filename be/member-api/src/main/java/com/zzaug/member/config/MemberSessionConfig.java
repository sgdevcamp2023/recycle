package com.zzaug.member.config;

import static com.zzaug.member.config.MemberRedisConfig.REDIS_CONNECTION_FACTORY_NAME;

import java.time.Duration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.session.data.redis.RedisIndexedSessionRepository;
import org.springframework.session.data.redis.config.annotation.SpringSessionRedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieHttpSessionIdResolver;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.session.web.http.HttpSessionIdResolver;

@Configuration
@EnableRedisHttpSession
public class MemberSessionConfig {
	public static final String SESSION_BEAN_NAME_PREFIX =
			MemberAppConfig.BEAN_NAME_PREFIX + "Session";
	private static final String HTTP_SESSION_ID_RESOLVER_BEAN_NAME =
			SESSION_BEAN_NAME_PREFIX + "HttpSessionIdResolver";
	private static final String COOKIE_SERIALIZER_BEAN_NAME =
			SESSION_BEAN_NAME_PREFIX + "CookieSerializer";
	private static final String SPRING_SESSION_DEFAULT_REDIS_SERIALIZER_BEAN_NAME =
			SESSION_BEAN_NAME_PREFIX + "SpringSessionDefaultRedisSerializer";
	private static final String MEMBER_SESSION_REPOSITORY_BEAN_NAME =
			SESSION_BEAN_NAME_PREFIX + "Repository";

	public static final String SESSION_NAME_SPACE = "zzuag:session";
	private static final String COOKIE_NAME = "JSESSIONID";
	private static final Boolean HTTP_ONLY = true;
	private static final Boolean SECURE = true;

	@Value("${cookie.domain}")
	private String domain;

	@Value("${cookie.path}")
	private String path;

	@Value("${security.jwt.token.validtime.access}")
	private Long accessExpireTime;

	@Bean(name = HTTP_SESSION_ID_RESOLVER_BEAN_NAME)
	public HttpSessionIdResolver cookieHttpSessionIdResolver() {
		CookieHttpSessionIdResolver cookieHttpSessionIdResolver = new CookieHttpSessionIdResolver();
		cookieHttpSessionIdResolver.setCookieSerializer(cookieSerializer());
		return cookieHttpSessionIdResolver;
	}

	@Bean(name = COOKIE_SERIALIZER_BEAN_NAME)
	public CookieSerializer cookieSerializer() {
		DefaultCookieSerializer serializer = new DefaultCookieSerializer();
		serializer.setCookieName(COOKIE_NAME);
		serializer.setCookiePath(path);
		serializer.setDomainNamePattern("^.+?\\.(\\w+\\.[a-z]+)$");
		serializer.setUseHttpOnlyCookie(HTTP_ONLY);
		serializer.setUseSecureCookie(SECURE);
		return serializer;
	}

	@Bean(name = SPRING_SESSION_DEFAULT_REDIS_SERIALIZER_BEAN_NAME)
	public RedisSerializer<Object> springSessionDefaultRedisSerializer() {
		return new GenericJackson2JsonRedisSerializer();
	}

	@SpringSessionRedisConnectionFactory
	@Bean
	public RedisConnectionFactory springSessionRedisConnectionFactory(
			@Qualifier(REDIS_CONNECTION_FACTORY_NAME) RedisConnectionFactory redisConnectionFactory) {
		return redisConnectionFactory;
	}

	@Primary
	@Bean(name = MEMBER_SESSION_REPOSITORY_BEAN_NAME)
	public RedisIndexedSessionRepository sessionRepository(
			@Qualifier("sessionRepository") RedisIndexedSessionRepository sessionRepository) {
		sessionRepository.setDefaultMaxInactiveInterval(
				(int) Duration.ofMillis(accessExpireTime).getSeconds());
		sessionRepository.setRedisKeyNamespace(SESSION_NAME_SPACE);
		return sessionRepository;
	}
}
