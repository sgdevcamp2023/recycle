package com.zzaug.review.web.controller.v1;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import com.epages.restdocs.apispec.SimpleType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzaug.review.ReviewApp;
import com.zzaug.review.web.controller.v1.description.CommentDescription;
import com.zzaug.review.web.controller.v1.description.Description;
import com.zzaug.review.web.dto.comment.CommentRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@ActiveProfiles(value = "test")
@AutoConfigureRestDocs
@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest(classes = ReviewApp.class)
public class CommentControllerTest {

	@Autowired private MockMvc mockMvc;
	@Autowired private ObjectMapper objectMapper;

	private static final String TAG = "CommentController";
	private static final String BASE_URL = "/api/v1";

	@Test
	@DisplayName("[POST] " + BASE_URL + "/questions/{question_id}/comments")
	void createComment() throws Exception {

		CommentRequest request =
				CommentRequest.builder().question_id(1L).content("content").parent_id(1L).build();

		String content = objectMapper.writeValueAsString(request);
		// set service mock

		mockMvc
				.perform(
						post(BASE_URL + "/questions/{question_id}/comments", 1)
								.content(content)
								.header("Authorization", "{{accessToken}}")
								.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful())
				.andDo(
						document(
								"CreateComment",
								resource(
										ResourceSnippetParameters.builder()
												.description("댓글 / 대댓글 생성")
												.tag(TAG)
												.requestSchema(Schema.schema("CommentRequest"))
												.responseSchema(Schema.schema("CommentResponse"))
												.requestHeaders(
														headerWithName("Authorization").description("{{accessToken}}"))
												.pathParameters(
														parameterWithName("question_id")
																.description("댓글을 달 질문 글의 id")
																.type(SimpleType.NUMBER))
												.responseFields(
														new FieldDescriptor[] {
															fieldWithPath("code").type(JsonFieldType.STRING).description("코드"),
															fieldWithPath("message")
																	.type(JsonFieldType.STRING)
																	.description("메시지"),
															fieldWithPath("data").ignored(),
														})
												.build())));
	}

	@Test
	@DisplayName("[GET] " + BASE_URL + "/questions/{question_id}/comments")
	void viewQuestionComment() throws Exception {
		mockMvc
				.perform(
						get(BASE_URL + "/questions/{question_id}/comments", 1)
								.header("Authorization", "{{accessToken}}")
								.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful())
				.andDo(
						document(
								"ViewQuestionComment",
								resource(
										ResourceSnippetParameters.builder()
												.description("질문 글에 달린 댓글 코멘트 조회")
												.tag(TAG)
												.pathParameters(
														parameterWithName("question_id")
																.description("댓글을 볼 질문 글의 id")
																.type(SimpleType.NUMBER))
												.requestHeaders(
														headerWithName("Authorization").description("{{accessToken}}"))
												.responseSchema(Schema.schema("ViewQuestionCommentResponse"))
												.responseFields(Description.success(CommentDescription.viewReview()))
												.build())));
	}

	@Test
	@DisplayName("[PUT] " + BASE_URL + "/questions/{question_id}/comments/{comment_id}")
	void editComment() throws Exception {

		CommentRequest request =
				CommentRequest.builder().question_id(1L).content("content").parent_id(1L).build();

		String content = objectMapper.writeValueAsString(request);

		mockMvc
				.perform(
						put(BASE_URL + "/questions/{question_id}/comments/{comment_id}", 1, 1)
								.content(content)
								.header("Authorization", "{{accessToken}}")
								.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful())
				.andDo(
						document(
								"EditComment",
								resource(
										ResourceSnippetParameters.builder()
												.description("댓글 수정")
												.tag(TAG)
												.requestHeaders(
														headerWithName("Authorization").description("{{accessToken}}"))
												.pathParameters(
														parameterWithName("question_id")
																.description("댓글이 달린 질문 글의 id")
																.type(SimpleType.NUMBER),
														parameterWithName("comment_id")
																.description("수정 할 댓글 id")
																.type(SimpleType.NUMBER))
												.requestSchema(Schema.schema("EditCommentRequest"))
												.responseSchema(Schema.schema("EditCommentResponse"))
												.responseFields(
														new FieldDescriptor[] {
															fieldWithPath("code").type(JsonFieldType.STRING).description("코드"),
															fieldWithPath("message")
																	.type(JsonFieldType.STRING)
																	.description("메시지"),
															fieldWithPath("data").ignored(),
														})
												.build())));
	}

	@Test
	@DisplayName("[DELETE] " + BASE_URL + "/questions/{question_id}/comments/{comment_id}")
	void deleteComment() throws Exception {

		mockMvc
				.perform(
						delete(BASE_URL + "/questions/{question_id}/comments/{comment_id}", 1, 1)
								.header("Authorization", "{{accessToken}}")
								.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful())
				.andDo(
						document(
								"DeleteComment",
								resource(
										ResourceSnippetParameters.builder()
												.description("댓글 삭제")
												.tag(TAG)
												.requestHeaders(
														headerWithName("Authorization").description("{{accessToken}}"))
												.pathParameters(
														parameterWithName("question_id")
																.description("댓글이 달린 질문 글의 id")
																.type(SimpleType.NUMBER),
														parameterWithName("comment_id")
																.description("삭제 할 댓글 id")
																.type(SimpleType.NUMBER))
												.responseSchema(Schema.schema("DeleteCommentResponse"))
												.responseFields(
														new FieldDescriptor[] {
															fieldWithPath("code").type(JsonFieldType.STRING).description("코드"),
															fieldWithPath("message")
																	.type(JsonFieldType.STRING)
																	.description("메시지"),
															fieldWithPath("data").ignored(),
														})
												.build())));
	}
}
