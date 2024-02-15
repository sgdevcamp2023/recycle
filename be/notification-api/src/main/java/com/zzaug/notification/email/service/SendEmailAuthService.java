package com.zzaug.notification.email.service;

import com.zzaug.notification.email.dto.AuthEmailDto;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Slf4j
@Component
@RequiredArgsConstructor
public class SendEmailAuthService {

	private static final String UTF_8 = "utf-8";
	private static final String SUBJECT = "ZZAUG 회원가입 인증코드입니다.";

	private final MailProperties notiMailProperties;
	private final JavaMailSender emailSender;
	private final SpringTemplateEngine templateEngine;

	public void sendSimpleMessage(AuthEmailDto dto) {
		final String to = dto.getTo();
		final String code = dto.getCode();

		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, UTF_8);
		try {
			helper.setFrom(notiMailProperties.getUsername());
			helper.setTo(to);
			helper.setSubject(SUBJECT);
			String html = getHtml(code);
			helper.setText(html, true);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		log.debug(">>>>> Sending email to: {}, subject: {}", to, SUBJECT);
		emailSender.send(message);
		log.debug("Email sent to {} <<<<<", to);
	}

	private String getHtml(String code) {
		Context context = new Context();
		context.setVariable("code", code);
		return templateEngine.process("email-auth", context);
	}
}
