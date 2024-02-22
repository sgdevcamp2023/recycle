package com.zzaug.notification.config;

import static com.zzaug.notification.config.NotificationAppConfig.BEAN_NAME_PREFIX;

import java.util.Properties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MaliConfig {

	private static final String MAIL_PROPERTIES_BEAN_NAME = BEAN_NAME_PREFIX + "MailProperties";
	private static final String MAIL_SENDER_BEAN_NAME = BEAN_NAME_PREFIX + "JavaMailSender";

	private static final String MAIL_SMTP_AUTH_KEY = "mail.smtp.auth";
	private static final String MAIL_SMTP_DEBUG_KEY = "mail.smtp.debug";
	private static final String MAIL_SMTP_STARTTLS_ENABLE_KEY = "mail.smtp.starttls.enable";
	private static final String MAIL_SMTP_ENABLE_SSL_ENABLE_KEY = "mail.smtp.EnableSSL.enable";

	@Bean(name = MAIL_PROPERTIES_BEAN_NAME)
	public MailProperties mailProperties() {
		return new MailProperties();
	}

	@Bean(name = MAIL_SENDER_BEAN_NAME)
	public JavaMailSender javaMailSender(
			@Qualifier(MAIL_PROPERTIES_BEAN_NAME) MailProperties mailProperties) {
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		javaMailSender.setProtocol(mailProperties.getProtocol());
		javaMailSender.setHost(mailProperties.getHost());
		javaMailSender.setPort(mailProperties.getPort());
		javaMailSender.setUsername(mailProperties.getUsername());
		javaMailSender.setPassword(mailProperties.getPassword());
		Properties prop = new Properties();
		prop.put(MAIL_SMTP_AUTH_KEY, mailProperties.getProperties().get(MAIL_SMTP_AUTH_KEY));
		prop.put(MAIL_SMTP_DEBUG_KEY, mailProperties.getProperties().get(MAIL_SMTP_DEBUG_KEY));
		prop.put(
				MAIL_SMTP_STARTTLS_ENABLE_KEY,
				mailProperties.getProperties().get(MAIL_SMTP_STARTTLS_ENABLE_KEY));
		prop.put(
				MAIL_SMTP_ENABLE_SSL_ENABLE_KEY,
				mailProperties.getProperties().get(MAIL_SMTP_ENABLE_SSL_ENABLE_KEY));
		javaMailSender.setJavaMailProperties(prop);
		return javaMailSender;
	}
}
