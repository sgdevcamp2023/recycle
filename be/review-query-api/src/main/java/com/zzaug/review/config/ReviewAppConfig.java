package com.zzaug.review.config;

import com.zzaug.flyway.FlywayConfig;
import com.zzaug.rabbitmq.config.ZRabbiMQConfig;
import com.zzaug.security.config.SecurityConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = ReviewAppConfig.BASE_PACKAGE)
@Import(value = {FlywayConfig.class, SecurityConfig.class, ZRabbiMQConfig.class})
public class ReviewAppConfig {

	public static final String BASE_PACKAGE = "com.zzaug.review";
	public static final String SERVICE_NAME = "recycle";
	public static final String MODULE_NAME = "review";
	public static final String BEAN_NAME_PREFIX = "Review";
	public static final String PROPERTY_PREFIX = SERVICE_NAME + "." + MODULE_NAME;
}
