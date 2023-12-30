package com.zzaug.flyway;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = FlywayConfig.BASE_PACKAGE)
public class FlywayConfig {
	public static final String BASE_PACKAGE = "com.zzaug.flyway";
	public static final String SERVICE_NAME = "zzaug";
	public static final String MODULE_NAME = "flyway";
	public static final String BEAN_NAME_PREFIX = "Flyway";
	public static final String PROPERTY_PREFIX = SERVICE_NAME + "." + MODULE_NAME;
}
