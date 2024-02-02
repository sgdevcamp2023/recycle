package com.zzaug.review.web.controller.v1.description;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

public class CommentDescription {

	public static FieldDescriptor[] viewReview() {
		return new FieldDescriptor[] {
			fieldWithPath("data").type(JsonFieldType.ARRAY).description("댓글"),
			fieldWithPath("data[].comment_id").type(JsonFieldType.NUMBER).description("댓글 id"),
			fieldWithPath("data[].question_id").type(JsonFieldType.NUMBER).description("댓글이 달린 질문 글 id"),
			fieldWithPath("data[].content").type(JsonFieldType.STRING).description("댓글 본문"),
			fieldWithPath("data[].author").type(JsonFieldType.STRING).description("댓글 작성자"),
			fieldWithPath("data[].author_id").type(JsonFieldType.NUMBER).description("댓글 작성자 id"),
			fieldWithPath("data[].parent_id").type(JsonFieldType.NUMBER).description("대댓글의 부모 댓글 고유번호"),
			fieldWithPath("data[].created_at").type(JsonFieldType.STRING).description("댓글 작성일자"),
			fieldWithPath("data[].updated_at").type(JsonFieldType.STRING).description("댓글 수정일자"),
		};
	}
}
