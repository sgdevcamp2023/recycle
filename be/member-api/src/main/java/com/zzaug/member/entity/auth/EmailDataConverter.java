package com.zzaug.member.entity.auth;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class EmailDataConverter implements AttributeConverter<EmailData, String> {

	@Override
	public String convertToDatabaseColumn(EmailData attribute) {
		return attribute.getEmail();
	}

	@Override
	public EmailData convertToEntityAttribute(String dbData) {
		return EmailData.builder().email(dbData).build();
	}
}
