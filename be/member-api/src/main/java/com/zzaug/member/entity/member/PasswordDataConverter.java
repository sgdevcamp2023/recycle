package com.zzaug.member.entity.member;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class PasswordDataConverter implements AttributeConverter<PasswordData, String> {

	@Override
	public String convertToDatabaseColumn(PasswordData attribute) {
		return attribute.getPassword();
	}

	@Override
	public PasswordData convertToEntityAttribute(String dbData) {
		return PasswordData.builder().password(dbData).build();
	}
}
