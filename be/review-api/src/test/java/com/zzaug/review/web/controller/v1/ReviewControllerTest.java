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
import com.zzaug.review.web.controller.v1.description.Description;
import com.zzaug.review.web.controller.v1.description.ReviewDescription;
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
				ReviewRequest.builder().content("content").location("location").tag("tag").build();

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
						.location("{location}")
						.tag("{tag}")
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
	@DisplayName("[GET] " + BASE_URL + "/me/reviews")
	void viewMemberReviewList() throws Exception {
		mockMvc
				.perform(
						get(BASE_URL + "/me/reviews")
								.header("Authorization", "{{accessToken}}")
								.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful())
				.andDo(
						document(
								"ViewMemberReviewList",
								resource(
										ResourceSnippetParameters.builder()
												.description("멤버가 작성한 리뷰 목록 조회")
												.tag(TAG)
												.requestHeaders(
														headerWithName("Authorization").description("{{accessToken}}"))
												.responseSchema(Schema.schema("ViewMemberReviewListResponse"))
												.responseFields(Description.success(ReviewDescription.viewReview()))
												.build())));
	}

	@Test
	@DisplayName("[GET] " + BASE_URL + "/questions/{question_id}/reviews")
	void viewQuestionReviewList() throws Exception {
		mockMvc
				.perform(
						get(BASE_URL + "/questions/{question_id}/reviews", 1)
								.header("Authorization", "{{accessToken}}")
								.param("question_id", "1")
								.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful())
				.andDo(
						document(
								"ViewQuestionReviewList",
								resource(
										ResourceSnippetParameters.builder()
												.description("질문 글에 달린 리뷰 목록 조회")
												.tag(TAG)
												.requestHeaders(
														headerWithName("Authorization").description("{{accessToken}}"))
												.pathParameters(
														parameterWithName("question_id")
																.description("리뷰 목록을 조회 할 질문 글 id")
																.type(SimpleType.NUMBER))
												.responseSchema(Schema.schema("ViewQuestionReviewListResponse"))
												.responseFields(Description.success(ReviewDescription.viewReview()))
												.build())));
	}

	@Test
	@DisplayName("[GET] " + BASE_URL + "/questions/{question_id}/reviews/temp")
	void viewTempReviewList() throws Exception {
		mockMvc
				.perform(
						get(BASE_URL + "/questions/{question_id}/reviews/temp", 1)
								.header("Authorization", "{{accessToken}}")
								.param("t_id", "UUID")
								.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful())
				.andDo(
						document(
								"ViewTempReviewList",
								resource(
										ResourceSnippetParameters.builder()
												.description("질문 글에 달린 임시 저장된 리뷰 목록 조회")
												.tag(TAG)
												.requestHeaders(
														headerWithName("Authorization").description("{{accessToken}}"))
												.pathParameters(
														parameterWithName("question_id")
																.description("임시 저장된 리뷰 목록을 조회 할 질문 글 id")
																.type(SimpleType.NUMBER))
												.requestParameters(
														parameterWithName("t_id")
																.description("임시 저장된 리뷰를 불러오기 위한 id, 없으면 목록으로 출력")
																.type(SimpleType.STRING)
																.optional())
												.responseSchema(Schema.schema("ViewTempReviewListResponse"))
												.responseFields(Description.success(ReviewDescription.viewTempReview()))
												.build())));
	}

	@Test
	@DisplayName("[PUT] " + BASE_URL + "/questions/{question_id}/reviews/{review_id}")
	void editReview() throws Exception {
		mockMvc
				.perform(
						put(BASE_URL + "/questions/{question_id}/reviews/{review_id}", 1, 1)
								.header("Authorization", "{{accessToken}}")
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
