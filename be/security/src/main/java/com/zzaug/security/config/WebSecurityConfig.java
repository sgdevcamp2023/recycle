package com.zzaug.security.config;

import com.zzaug.security.authentication.token.TokenAuthProvider;
import com.zzaug.security.filter.exception.TokenInvalidExceptionHandlerFilter;
import com.zzaug.security.filter.token.TokenAuthenticationFilter;
import com.zzaug.security.handler.DelegatedAccessDeniedHandler;
import com.zzaug.security.handler.DelegatedAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.OncePerRequestFilter;

@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

	private final DelegatedAuthenticationEntryPoint authenticationEntryPoint;
	private final DelegatedAccessDeniedHandler accessDeniedHandler;
	private final TokenAuthProvider tokenAuthProvider;
	private final CorsConfigurationSourceProperties corsProperties;

	@Bean
	@Profile("!prod")
	public SecurityFilterChain localSecurityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.formLogin().disable();
		http.httpBasic().disable();
		http.cors().configurationSource(corsConfigurationSource());

		http.authorizeRequests().antMatchers("/api/v1/**").authenticated().anyRequest().denyAll();

		http.addFilterBefore(
				getTokenInvalidExceptionHandlerFilter(), AbstractPreAuthenticatedProcessingFilter.class);
		http.addFilterAt(
				generateAuthenticationFilter(), AbstractPreAuthenticatedProcessingFilter.class);

		http.exceptionHandling()
				.authenticationEntryPoint(authenticationEntryPoint)
				.accessDeniedHandler(accessDeniedHandler);

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		return http.build();
	}

	@Bean
	@Profile(value = "prod")
	public SecurityFilterChain prodSecurityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.formLogin().disable();
		http.httpBasic().disable();
		http.cors().configurationSource(corsConfigurationSource());

		http.authorizeRequests().antMatchers("/api/v1/**").authenticated().anyRequest().denyAll();

		http.addFilterBefore(
				getTokenInvalidExceptionHandlerFilter(), AbstractPreAuthenticatedProcessingFilter.class);
		http.addFilterAt(
				generateAuthenticationFilter(), AbstractPreAuthenticatedProcessingFilter.class);

		http.exceptionHandling()
				.authenticationEntryPoint(authenticationEntryPoint)
				.accessDeniedHandler(accessDeniedHandler);

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		return http.build();
	}

	public TokenAuthenticationFilter generateAuthenticationFilter() {
		TokenAuthenticationFilter tokenAuthenticationFilter = new TokenAuthenticationFilter();
		tokenAuthenticationFilter.setAuthenticationManager(new ProviderManager(tokenAuthProvider));
		return tokenAuthenticationFilter;
	}

	public OncePerRequestFilter getTokenInvalidExceptionHandlerFilter() {
		return new TokenInvalidExceptionHandlerFilter();
	}

	@Bean
	@Profile("!prod")
	public WebSecurityCustomizer localWebSecurityFilterIgnoreCustomizer() {
		return web ->
				web.ignoring()
						.antMatchers(
								HttpMethod.GET,
								"/actuator/health",
								"/error",
								"/swagger-ui/*",
								"/swagger-resources/**",
								"/v3/api-docs/**",
								"/openapi3.yaml",
								"/reports/**",
								"/api/v1/members/check")
						.antMatchers(HttpMethod.POST, "/api/v1/members");
	}

	@Bean
	@Profile("prod")
	public WebSecurityCustomizer prodWebSecurityFilterIgnoreCustomizer() {
		return web ->
				web.ignoring()
						.antMatchers(
								HttpMethod.GET,
								"/actuator/health",
								"/error",
								"/swagger-ui/*",
								"/swagger-resources/**",
								"/v3/api-docs/**",
								"/openapi3.yaml",
								"/reports/**",
								"/api/v1/members/check")
						.antMatchers(HttpMethod.POST, "/api/v1/members");
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();

		configuration.addAllowedOriginPattern(corsProperties.getOriginPattern());
		configuration.addAllowedHeader(corsProperties.getAllowedHeaders());
		configuration.addAllowedMethod(corsProperties.getAllowedMethods());
		configuration.setAllowCredentials(corsProperties.getAllowCredentials());

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration(corsProperties.getPathPattern(), configuration);
		return source;
	}
}
