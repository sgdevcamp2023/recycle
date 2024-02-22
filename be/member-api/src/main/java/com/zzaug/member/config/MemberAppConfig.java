package com.zzaug.member.config;

import com.zzaug.flyway.FlywayConfig;
import com.zzaug.rabbitmq.config.ZRabbiMQConfig;
import com.zzaug.web.config.WebModuleConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = MemberAppConfig.BASE_PACKAGE)
@Import(value = {FlywayConfig.class, WebModuleConfig.class, ZRabbiMQConfig.class})
public class MemberAppConfig {

	public static final String BASE_PACKAGE = "com.zzaug.member";
	public static final String SERVICE_NAME = "recycle";
	public static final String MODULE_NAME = "member";
	public static final String BEAN_NAME_PREFIX = "member";
	public static final String PROPERTY_PREFIX = SERVICE_NAME + "." + MODULE_NAME;
}
