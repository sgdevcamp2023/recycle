// package com.zzaug.member.entity.auth;
//
// import javax.persistence.AttributeConverter;
// import javax.persistence.Converter;
//
// @Converter(autoApply = true)
// public class TokenDataConverter implements AttributeConverter<TokenData, String> {
//
//	@Override
//	public String convertToDatabaseColumn(TokenData attribute) {
//		return attribute.getToken();
//	}
//
//	@Override
//	public TokenData convertToEntityAttribute(String dbData) {
//		return TokenData.builder().token(dbData).build();
//	}
// }
