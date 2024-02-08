package com.zzaug.member.web.controller.v1.description;

import static com.epages.restdocs.apispec.ResourceDocumentation.headerWithName;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

import com.epages.restdocs.apispec.HeaderDescriptorWithType;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

public class Description {

	private static FieldDescriptor getSuccessCodeDescriptor() {
		return fieldWithPath("code").type(JsonFieldType.STRING).description("success");
	}

	private static FieldDescriptor getSuccessMessageDescriptor() {
		return fieldWithPath("message").type(JsonFieldType.STRING).description("성공");
	}

	public static FieldDescriptor[] success(FieldDescriptor[] data) {
		return ArrayUtils.addAll(data, getSuccessMessageDescriptor(), getSuccessCodeDescriptor());
	}

	public static FieldDescriptor[] success() {
		return new FieldDescriptor[] {getSuccessMessageDescriptor(), getSuccessCodeDescriptor()};
	}

	private static FieldDescriptor getCreateCodeDescriptor() {
		return fieldWithPath("code").type(JsonFieldType.STRING).description("resource.created");
	}

	private static FieldDescriptor getCreateMessageDescriptor() {
		return fieldWithPath("message").type(JsonFieldType.STRING).description("새로 생성되었습니다.");
	}

	private static FieldDescriptor getModifiedCodeDescriptor() {
		return fieldWithPath("code").type(JsonFieldType.STRING).description("resource.modified");
	}

	private static FieldDescriptor getModifiedMessageDescriptor() {
		return fieldWithPath("message").type(JsonFieldType.STRING).description("수정되었습니다.");
	}

	private static FieldDescriptor getDeletedCodeDescriptor() {
		return fieldWithPath("code").type(JsonFieldType.STRING).description("resource.deleted");
	}

	private static FieldDescriptor getDeletedMessageDescriptor() {
		return fieldWithPath("message").type(JsonFieldType.STRING).description("삭제되었습니다.");
	}

	public static FieldDescriptor[] created() {
		return new FieldDescriptor[] {getCreateCodeDescriptor(), getCreateMessageDescriptor()};
	}

	public static FieldDescriptor[] fail() {
		return new FieldDescriptor[] {getFailCodeDescriptor(), getFailMessageDescriptor()};
	}

	public static FieldDescriptor[] modified() {
		return new FieldDescriptor[] {getModifiedCodeDescriptor(), getModifiedMessageDescriptor()};
	}

	public static FieldDescriptor[] deleted() {
		return new FieldDescriptor[] {getDeletedCodeDescriptor(), getDeletedMessageDescriptor()};
	}

	private static FieldDescriptor getFailCodeDescriptor() {
		return fieldWithPath("code").type(JsonFieldType.STRING).description("code");
	}

	private static FieldDescriptor getFailMessageDescriptor() {
		return fieldWithPath("message").type(JsonFieldType.STRING).description("message");
	}

	public static HeaderDescriptorWithType authHeader() {
		return headerWithName("Authorization")
				.defaultValue("{{accessToken}}")
				.description("Bearer 어세스 토큰");
	}

	public static HeaderDescriptorWithType refererHeader() {
		return headerWithName("Referer").description("이전 주소 정보");
	}

	public static HeaderDescriptorWithType xZzaugIdHeader() {
		return headerWithName("X-ZZAUG-ID").description("요청 추적을 위한 uuid");
	}
}
