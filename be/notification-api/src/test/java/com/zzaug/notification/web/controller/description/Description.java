package com.zzaug.notification.web.controller.description;

import static com.epages.restdocs.apispec.ResourceDocumentation.headerWithName;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

import com.epages.restdocs.apispec.HeaderDescriptorWithType;
import java.util.UUID;
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

	private static FieldDescriptor getTimeStampDescriptor() {
		return fieldWithPath("timestamp").type(JsonFieldType.STRING).description("응답 시간");
	}

	public static FieldDescriptor[] success(FieldDescriptor[] data) {
		return ArrayUtils.addAll(
				data, getSuccessMessageDescriptor(), getSuccessCodeDescriptor(), getTimeStampDescriptor());
	}

	public static FieldDescriptor[] success() {
		return new FieldDescriptor[] {
			getSuccessMessageDescriptor(), getSuccessCodeDescriptor(), getTimeStampDescriptor()
		};
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
		return new FieldDescriptor[] {
			getCreateCodeDescriptor(), getCreateMessageDescriptor(), getTimeStampDescriptor()
		};
	}

	public static FieldDescriptor[] fail() {
		return new FieldDescriptor[] {
			getFailCodeDescriptor(), getFailMessageDescriptor(), getTimeStampDescriptor()
		};
	}

	public static FieldDescriptor[] modified() {
		return new FieldDescriptor[] {
			getModifiedCodeDescriptor(), getModifiedMessageDescriptor(), getTimeStampDescriptor()
		};
	}

	public static FieldDescriptor[] deleted() {
		return new FieldDescriptor[] {
			getDeletedCodeDescriptor(), getDeletedMessageDescriptor(), getTimeStampDescriptor()
		};
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

	public static HeaderDescriptorWithType xZzaugIdHeader() {
		return headerWithName("X-ZZAUG-ID")
				.description("요청 추적을 위한 uuid")
				.defaultValue(UUID.randomUUID());
	}

	public static HeaderDescriptorWithType setCookieHeader() {
		return headerWithName("Set-Cookie")
				.defaultValue(
						"refreshToken=refreshToken; Path=/; HttpOnly; Domain=swaggerhub.com; SameSite=Lax; Secure")
				.description("리프레시 토큰 쿠키");
	}
}
