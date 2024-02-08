package com.zzaug.web.config;

import com.zzaug.security.config.SecurityConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = WebModuleConfig.BASE_PACKAGE)
@Import({SecurityConfig.class})
public class WebModuleConfig {
	public static final String BASE_PACKAGE = "com.zzaug.web";
	public static final String SERVICE_NAME = "recycle";
	public static final String MODULE_NAME = "web";
	public static final String BEAN_NAME_PREFIX = "Web";
	public static final String PROPERTY_PREFIX = SERVICE_NAME + "." + MODULE_NAME;
}
