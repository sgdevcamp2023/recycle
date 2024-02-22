package com.zzaug.security.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class CorsConfigurationSourceProperties {

	@Value("${security.cors.path-patterns}")
	private String pathPattern;

	@Value("${security.cors.origin-patterns}")
	private String originPattern;

	@Value("${security.cors.allowed-methods}")
	private String allowedMethods;

	@Value("${security.cors.allowed-headers}")
	private String allowedHeaders;

	@Value("${security.cors.exposed-headers}")
	private String exposedHeaders;

	@Value("${security.cors.allow-credentials}")
	private Boolean allowCredentials;

	@Value("${security.cors.max-age}")
	private Long maxAge;
}
