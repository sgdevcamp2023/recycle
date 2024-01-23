package com.zzaug.review.web.controller.v1.description;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

public class MemberDescription {
	public static FieldDescriptor[] reviewerList() {
		return new FieldDescriptor[] {
			fieldWithPath("data").type(JsonFieldType.ARRAY).description("질문"),
			fieldWithPath("data[].question_id").type(JsonFieldType.NUMBER).description("질문 글 id"),
			fieldWithPath("data[].author").type(JsonFieldType.STRING).description("질문 글 작성자"),
			fieldWithPath("data[].author_id").type(JsonFieldType.NUMBER).description("질문 글 작성자 id"),
		};
	}
}
