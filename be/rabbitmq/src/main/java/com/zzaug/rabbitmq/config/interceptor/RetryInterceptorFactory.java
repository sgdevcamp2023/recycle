package com.zzaug.rabbitmq.config.interceptor;

import com.zzaug.rabbitmq.config.handler.ZRMQExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.MessageRecoverer;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RetryInterceptorFactory {

	private final int DEFAULT_MAX_TRY_COUNT = 3;
	private final int DEFAULT_INITIAL_INTERVAL = 1000 * 3;
	private final int DEFAULT_MULTIPLIER = 3;
	private final int DEFAULT_MAX_INTERVAL = 1000 * 10;

	private final RabbitTemplate rabbitTemplate;

	public MethodInterceptor getRetryInterceptor(
			MessageRecoverer recoverer,
			int maxTryCount,
			int initialInterval,
			int multiplier,
			int maxInterval) {
		return RetryInterceptorBuilder.stateless()
				.maxAttempts(maxTryCount)
				.backOffOptions(initialInterval, multiplier, maxInterval)
				.recoverer(recoverer)
				.build();
	}

	/**
	 * Default RetryInterceptor
	 *
	 * <p>10초 내에 3번 시도하고, 3초 간격으로 재시도한다.
	 *
	 * <p>발생한 예외는 ZRMQExceptionHandler를 통해 Dead Letter Queue로 보낸다.
	 */
	public MethodInterceptor getRetryInterceptor() {
		return this.getRetryInterceptor(
				new ZRMQExceptionHandler(rabbitTemplate),
				DEFAULT_MAX_TRY_COUNT,
				DEFAULT_INITIAL_INTERVAL,
				DEFAULT_MULTIPLIER,
				DEFAULT_MAX_INTERVAL);
	}
}
