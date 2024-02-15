package com.zzaug.notification.config;

import com.zzaug.flyway.FlywayConfig;
import com.zzaug.rabbitmq.config.ZRabbiMQConfig;
import com.zzaug.security.config.SecurityConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = NotificationAppConfig.BASE_PACKAGE)
@Import(value = {FlywayConfig.class, SecurityConfig.class, ZRabbiMQConfig.class})
public class NotificationAppConfig {

	public static final String BASE_PACKAGE = "com.zzaug.notification";
	public static final String SERVICE_NAME = "recycle";
	public static final String MODULE_NAME = "notification";
	public static final String BEAN_NAME_PREFIX = "noti";
	public static final String PROPERTY_PREFIX = SERVICE_NAME + "." + MODULE_NAME;
}
