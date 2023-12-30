package com.zzaug.member.config;

import com.zzaug.flyway.FlywayConfig;
import com.zzaug.security.config.SecurityConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = MemberAppConfig.BASE_PACKAGE)
@Import(value = {FlywayConfig.class, SecurityConfig.class})
public class MemberAppConfig {

	public static final String BASE_PACKAGE = "com.zzaug.member";
	public static final String SERVICE_NAME = "recycle";
	public static final String MODULE_NAME = "member";
	public static final String BEAN_NAME_PREFIX = "Member";
	public static final String PROPERTY_PREFIX = SERVICE_NAME + "." + MODULE_NAME;
}
