package com.zzaug.member.entity.member;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class CertificationDataConverter implements AttributeConverter<CertificationData, String> {

	@Override
	public String convertToDatabaseColumn(CertificationData attribute) {
		return attribute.getCertification();
	}

	@Override
	public CertificationData convertToEntityAttribute(String dbData) {
		return CertificationData.builder().certification(dbData).build();
	}
}
