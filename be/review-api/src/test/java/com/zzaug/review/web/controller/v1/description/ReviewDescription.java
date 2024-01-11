package com.zzaug.review.web.controller.v1.description;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

public class ReviewDescription {
	public static FieldDescriptor[] viewReview() {
		return new FieldDescriptor[] {
			fieldWithPath("data").type(JsonFieldType.ARRAY).description("리뷰"),
			fieldWithPath("data[].review_id").type(JsonFieldType.NUMBER).description("질문 글에 달린 리뷰 id"),
			fieldWithPath("data[].question_id").type(JsonFieldType.NUMBER).description("질문 글 id"),
			fieldWithPath("data[].content").type(JsonFieldType.STRING).description("리뷰 본문"),
			fieldWithPath("data[].location").type(JsonFieldType.STRING).description("리뷰 위치"),
			fieldWithPath("data[].author").type(JsonFieldType.STRING).description("리뷰 작성자"),
			fieldWithPath("data[].author_id").type(JsonFieldType.NUMBER).description("리뷰 작성자 id"),
			fieldWithPath("data[].created_at").type(JsonFieldType.STRING).description("리뷰 작성일자"),
			fieldWithPath("data[].updated_at").type(JsonFieldType.STRING).description("리뷰 수정일자"),
			fieldWithPath("data[].tag").type(JsonFieldType.STRING).description("리뷰 태그"),
		};
	}

	public static FieldDescriptor[] viewTempReview() {
		return new FieldDescriptor[] {
			fieldWithPath("data").type(JsonFieldType.ARRAY).description("리뷰"),
			fieldWithPath("data[].t_id").type(JsonFieldType.STRING).description("질문 글에 달린 리뷰 임시 id"),
			fieldWithPath("data[].question_id").type(JsonFieldType.NUMBER).description("질문 글 id"),
			fieldWithPath("data[].content").type(JsonFieldType.STRING).description("리뷰 본문"),
			fieldWithPath("data[].location").type(JsonFieldType.STRING).description("리뷰 위치"),
			fieldWithPath("data[].author").type(JsonFieldType.STRING).description("리뷰 작성자"),
			fieldWithPath("data[].author_id").type(JsonFieldType.NUMBER).description("리뷰 작성자 id"),
			fieldWithPath("data[].created_at").type(JsonFieldType.STRING).description("리뷰 작성일자"),
			fieldWithPath("data[].updated_at").type(JsonFieldType.STRING).description("리뷰 수정일자"),
			fieldWithPath("data[].tag").type(JsonFieldType.STRING).description("리뷰에 달린 리뷰 갯수"),
		};
	}
}
