package com.zzaug.review.web.controller.v1.description;

import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public class QuestionDescription {
	public static FieldDescriptor[] viewQuestion() {
		return new FieldDescriptor[] {
			fieldWithPath("data").type(JsonFieldType.OBJECT).description("질문"),
			fieldWithPath("data.question_id").type(JsonFieldType.NUMBER).description("질문 글 id"),
			fieldWithPath("data.content").type(JsonFieldType.STRING).description("질문 글 본문"),
			fieldWithPath("data.author").type(JsonFieldType.STRING).description("질문 글 작성자"),
			fieldWithPath("data.author_id").type(JsonFieldType.NUMBER).description("질문 글 작성자 id"),
			fieldWithPath("data.review_cnt").type(JsonFieldType.NUMBER).description("질문 글에 달린 리뷰 갯수"),
			fieldWithPath("data.created_at").type(JsonFieldType.STRING).description("질문 글 작성일자"),
			fieldWithPath("data.updated_at").type(JsonFieldType.STRING).description("질문 글 수정일자"),
		};
	}

	public static FieldDescriptor[] viewQuestionList() {
		return new FieldDescriptor[] {
			fieldWithPath("data").type(JsonFieldType.ARRAY).description("질문"),
			fieldWithPath("data[].question_id").type(JsonFieldType.NUMBER).description("질문 글 id"),
			fieldWithPath("data[].content").type(JsonFieldType.STRING).description("질문 글 본문"),
			fieldWithPath("data[].author").type(JsonFieldType.STRING).description("질문 글 작성자"),
			fieldWithPath("data[].author_id").type(JsonFieldType.NUMBER).description("질문 글 작성자 id"),
			fieldWithPath("data[].review_cnt").type(JsonFieldType.NUMBER).description("질문 글에 달린 리뷰 갯수"),
			fieldWithPath("data[].created_at").type(JsonFieldType.STRING).description("질문 글 작성일자"),
			fieldWithPath("data[].updated_at").type(JsonFieldType.STRING).description("질문 글 수정일자"),
		};
	}

	public static FieldDescriptor[] viewTempQuestionList() {
		return new FieldDescriptor[] {
			fieldWithPath("data").type(JsonFieldType.ARRAY).description("임시 저장된 질문"),
			fieldWithPath("data[].t_id").type(JsonFieldType.STRING).description("임시 저장된 질문 글 id"),
			fieldWithPath("data[].content").type(JsonFieldType.STRING).description("임시 저장된 질문 글 본문"),
			fieldWithPath("data[].author").type(JsonFieldType.STRING).description("임시 저장된 질문 글 작성자"),
			fieldWithPath("data[].author_id")
					.type(JsonFieldType.NUMBER)
					.description("임시 저장된 질문 글 작성자 id"),
			fieldWithPath("data[].created_at").type(JsonFieldType.STRING).description("임시 저장된 질문 글 작성일자"),
		};
	}
}
