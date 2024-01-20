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
import com.zzaug.review.entity.review.ReviewType;
import com.zzaug.review.web.dto.review.ReviewRequest;
import com.zzaug.review.web.dto.review.ReviewTempRequest;
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
class ReviewControllerTest {
	@Autowired private MockMvc mockMvc;
	@Autowired private ObjectMapper objectMapper;
	private static final String TAG = "ReviewController";
	private static final String BASE_URL = "/api/v1";

	@Test
	@DisplayName("[POST] " + BASE_URL + "/questions/{question_id}/reviews")
	void createReview() throws Exception {

		ReviewRequest request =
				ReviewRequest.builder()
						.content("content")
						.tag(ReviewType.CODE)
						.build();

		String content = objectMapper.writeValueAsString(request);
		// set service mock

		mockMvc
				.perform(
						post(BASE_URL + "/questions/{question_id}/reviews", 1)
								.content(content)
								.param("question_id", "1")
								.header("Authorization", "{{accessToken}}")
								.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful())
				.andDo(
						document(
								"CreateReview",
								resource(
										ResourceSnippetParameters.builder()
												.description("리뷰 생성")
												.tag(TAG)
												.requestSchema(Schema.schema("ReviewRequest"))
												.responseSchema(Schema.schema("ReviewResponse"))
												.requestHeaders(
														headerWithName("Authorization").description("{{accessToken}}"))
												.pathParameters(
														parameterWithName("question_id")
																.description("리뷰를 달 질문 글의 id")
																.type(SimpleType.NUMBER))
												.responseFields(
														new FieldDescriptor[] {
															fieldWithPath("code").type(JsonFieldType.STRING).description("코드"),
															fieldWithPath("message")
																	.type(JsonFieldType.STRING)
																	.description("메시지"),
															fieldWithPath("data").type(JsonFieldType.NULL).description("null"),
														})
												.build())));
	}

	@Test
	@DisplayName("[POST] " + BASE_URL + "/questions/{question_id}/reviews/temp")
	void createTempReview() throws Exception {

		ReviewTempRequest request =
				ReviewTempRequest.builder()
						.t_id("{UUID}")
						.content("{content}")
						.tag(ReviewType.CODE)
						.build();

		String content = objectMapper.writeValueAsString(request);
		// set service mock

		mockMvc
				.perform(
						post(BASE_URL + "/questions/{question_id}/reviews/temp", 1)
								.content(content)
								.param("question_id", "1")
								.header("Authorization", "{{accessToken}}")
								.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful())
				.andDo(
						document(
								"CreateTempReview",
								resource(
										ResourceSnippetParameters.builder()
												.description("리뷰 임시 저장")
												.tag(TAG)
												.requestSchema(Schema.schema("ReviewRequest"))
												.responseSchema(Schema.schema("ReviewResponse"))
												.requestHeaders(
														headerWithName("Authorization").description("{{accessToken}}"))
												.pathParameters(
														parameterWithName("question_id")
																.description("리뷰를 달 질문 글의 id")
																.type(SimpleType.NUMBER))
												.responseFields(
														new FieldDescriptor[] {
															fieldWithPath("code").type(JsonFieldType.STRING).description("코드"),
															fieldWithPath("message")
																	.type(JsonFieldType.STRING)
																	.description("메시지"),
															fieldWithPath("data").type(JsonFieldType.NULL).description("null"),
														})
												.build())));
	}

	@Test
	@DisplayName("[PUT] " + BASE_URL + "/questions/{question_id}/reviews/{review_id}")
	void editReview() throws Exception {

		ReviewRequest request =
				ReviewRequest.builder()
						.content("content")
						.tag(ReviewType.CODE)
						.build();

		String content = objectMapper.writeValueAsString(request);

		mockMvc
				.perform(
						put(BASE_URL + "/questions/{question_id}/reviews/{review_id}", 1, 1)
								.header("Authorization", "{{accessToken}}")
								.content(content)
								.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful())
				.andDo(
						document(
								"EditReview",
								resource(
										ResourceSnippetParameters.builder()
												.description("리뷰 수정")
												.tag(TAG)
												.requestHeaders(
														headerWithName("Authorization").description("{{accessToken}}"))
												.pathParameters(
														parameterWithName("question_id")
																.description("리뷰가 달린 질문 글의 id")
																.type(SimpleType.NUMBER),
														parameterWithName("review_id")
																.description("수정 할 리뷰 id")
																.type(SimpleType.NUMBER))
												.requestSchema(Schema.schema("ReviewRequest"))
												.responseSchema(Schema.schema("EditReviewResponse"))
												.responseFields(
														new FieldDescriptor[] {
															fieldWithPath("code").type(JsonFieldType.STRING).description("코드"),
															fieldWithPath("message")
																	.type(JsonFieldType.STRING)
																	.description("메시지"),
															fieldWithPath("data").type(JsonFieldType.NULL).description("null"),
														})
												.build())));
	}

	@Test
	@DisplayName("[DELETE] " + BASE_URL + "/questions/{question_id}/reviews/{review_id}")
	void deleteReview() throws Exception {
		mockMvc
				.perform(
						delete(BASE_URL + "/questions/{question_id}/reviews/{review_id}", 1, 1)
								.header("Authorization", "{{accessToken}}")
								.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful())
				.andDo(
						document(
								"DeleteReview",
								resource(
										ResourceSnippetParameters.builder()
												.description("리뷰 삭제")
												.tag(TAG)
												.requestHeaders(
														headerWithName("Authorization").description("{{accessToken}}"))
												.pathParameters(
														parameterWithName("question_id")
																.description("리뷰가 달린 질문 글의 id")
																.type(SimpleType.NUMBER),
														parameterWithName("review_id")
																.description("삭제 할 리뷰 id")
																.type(SimpleType.NUMBER))
												.responseSchema(Schema.schema("EditReviewResponse"))
												.responseFields(
														new FieldDescriptor[] {
															fieldWithPath("code").type(JsonFieldType.STRING).description("코드"),
															fieldWithPath("message")
																	.type(JsonFieldType.STRING)
																	.description("메시지"),
															fieldWithPath("data").type(JsonFieldType.NULL).description("null"),
														})
												.build())));
	}
}
