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
import com.zzaug.review.web.controller.v1.description.MemberDescription;
import com.zzaug.review.web.controller.v1.description.QuestionDescription;
import com.zzaug.review.web.dto.question.QuestionRequest;
import com.zzaug.review.web.dto.question.QuestionTempRequest;
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
class QuestionControllerTest {

	@Autowired private MockMvc mockMvc;
	@Autowired private ObjectMapper objectMapper;

	private static final String TAG = "QuestionController";
	private static final String BASE_URL = "/api/v1";

	@Test
	@DisplayName("[POST] " + BASE_URL + "/questions")
	void createQuestion() throws Exception {
		QuestionRequest request = QuestionRequest.builder().content("content").build();

		String content = objectMapper.writeValueAsString(request);

		mockMvc
				.perform(
						post(BASE_URL + "/questions", 0)
								.header("Authorization", "{{accessToken")
								.content(content)
								.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful())
				.andDo(
						document(
								"CreateQuestion",
								resource(
										ResourceSnippetParameters.builder()
												.description("질문 글 생성")
												.tag(TAG)
												.requestSchema(Schema.schema("QuestionRequest"))
												.requestHeaders(
														headerWithName("Authorization").description("{{accessToken}}"))
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
	@DisplayName("[POST] " + BASE_URL + "/questions/temp")
	void createTempQuestion() throws Exception {

		QuestionTempRequest request =
				QuestionTempRequest.builder().t_id("UUID").content("content").build();

		String content = objectMapper.writeValueAsString(request);

		mockMvc
				.perform(
						post(BASE_URL + "/questions/temp", 0)
								.header("Authorization", "{{accessToken")
								.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful())
				.andDo(
						document(
								"CreateTempQuestion",
								resource(
										ResourceSnippetParameters.builder()
												.description("질문 글 임시 저장")
												.tag(TAG)
												.requestSchema(Schema.schema("QuestionTempRequest"))
												.requestHeaders(
														headerWithName("Authorization").description("{{accessToken}}"))
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
	@DisplayName("[GET] " + BASE_URL + "/questions/{question_id}")
	void viewQuestion() throws Exception {

		mockMvc
				.perform(
						get(BASE_URL + "/questions/{question_id}", 1)
								.param("question_id", "1")
								.header("Authorization", "{{accessToken")
								.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful())
				.andDo(
						document(
								"ViewQuestion",
								resource(
										ResourceSnippetParameters.builder()
												.description("질문 글 조회")
												.tag(TAG)
												.responseSchema(Schema.schema("QuestionResponse"))
												.requestHeaders(
														headerWithName("Authorization").description("{{accessToken}}"))
												.pathParameters(
														parameterWithName("question_id")
																.description("조회 할 질문 글 id")
																.type(SimpleType.NUMBER))
												.responseFields(Description.success(QuestionDescription.viewQuestion()))
												.build())));
	}

	@Test
	@DisplayName("[GET] " + BASE_URL + "/me/questions")
	void viewQuestionList() throws Exception {
		mockMvc
				.perform(
						get(BASE_URL + "/me/questions")
								.header("Authorization", "{{accessToken")
								.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful())
				.andDo(
						document(
								"ViewQuestionList",
								resource(
										ResourceSnippetParameters.builder()
												.description("질문 글 목록 조회")
												.tag(TAG)
												.requestHeaders(
														headerWithName("Authorization").description("{{accessToken}}"))
												.responseSchema(Schema.schema("ViewQuestionListResponse"))
												.responseFields(Description.success(QuestionDescription.viewQuestionList()))
												.build())));
	}

	@Test
	@DisplayName("[GET] " + BASE_URL + "/me/questions/temp")
	void viewTempQuestionList() throws Exception {
		mockMvc
				.perform(
						get(BASE_URL + "/me/questions/temp")
								.header("Authorization", "{{accessToken")
								.param("t_id", "UUID:nullable")
								.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful())
				.andDo(
						document(
								"ViewTempQuestionList",
								resource(
										ResourceSnippetParameters.builder()
												.description("임시 저장된 질문 글 목록 조회")
												.tag(TAG)
												.requestHeaders(
														headerWithName("Authorization").description("{{accessToken}}"))
												.requestParameters(
														parameterWithName("t_id")
																.description("조회 할 임시 저장된 질문 글 id, null이면 목록 조회"))
												.responseSchema(Schema.schema("ViewTempQuestionListResponse"))
												.responseFields(
														Description.success(QuestionDescription.viewTempQuestionList()))
												.build())));
	}

	@Test
	@DisplayName("[GET] " + BASE_URL + "/questions/search")
	void searchQuestionList() throws Exception {
		mockMvc
				.perform(
						get(BASE_URL + "/questions/search")
								.header("Authorization", "{{accessToken")
								.param("me", "true")
								.param("query", "target")
								.param("page", "1")
								.param("size", "16")
								.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful())
				.andDo(
						document(
								"SearchQuestionList",
								resource(
										ResourceSnippetParameters.builder()
												.description("멤버의 질문 글 목록에서 검색")
												.tag(TAG)
												.requestHeaders(
														headerWithName("Authorization").description("{{accessToken}}"))
												.requestParameters(
														parameterWithName("me")
																.description("true면 멤버의 질문 글 조회, false면 전체 조회 (Default : true)")
																.defaultValue(true)
																.type(SimpleType.BOOLEAN),
														parameterWithName("query").description("검색 내용"),
														parameterWithName("page")
																.description("요청 할 페이지")
																.type(SimpleType.NUMBER),
														parameterWithName("size")
																.description("페이지 안의 갯수")
																.type(SimpleType.NUMBER))
												.responseSchema(Schema.schema("SearchQuestionListResponse"))
												.responseFields(Description.success(QuestionDescription.viewQuestionList()))
												.build())));
	}

	@Test
	@DisplayName("[DELETE] " + BASE_URL + "/questions/{question_id}")
	void deleteQuestion() throws Exception {

		mockMvc
				.perform(
						delete(BASE_URL + "/questions/{question_id}", 1)
								.param("question_id", "1")
								.header("Authorization", "{{accessToken")
								.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful())
				.andDo(
						document(
								"DeleteQuestion",
								resource(
										ResourceSnippetParameters.builder()
												.description("질문 글 삭제")
												.tag(TAG)
												.requestHeaders(
														headerWithName("Authorization").description("{{accessToken}}"))
												.responseSchema(Schema.schema("QuestionResponse"))
												.pathParameters(
														parameterWithName("question_id")
																.description("삭제 할 질문 글 id")
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
	@DisplayName("[GET] " + BASE_URL + "/me/requests/reviews")
	void viewQuestionRequestList() throws Exception {
		mockMvc
				.perform(
						get(BASE_URL + "/me/requests/reviews")
								.header("Authorization", "{{accessToken")
								.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful())
				.andDo(
						document(
								"ViewQuestionRequestList",
								resource(
										ResourceSnippetParameters.builder()
												.description("리뷰 요청 목록 조회")
												.tag(TAG)
												.requestHeaders(
														headerWithName("Authorization").description("{{accessToken}}"))
												.responseSchema(Schema.schema("ViewQuestionRequestListResponse"))
												.responseFields(Description.success(QuestionDescription.viewQuestionList()))
												.build())));
	}

	@Test
	@DisplayName("[GET] " + BASE_URL + "/me/questions/reviewers")
	void viewReviewerList() throws Exception {
		mockMvc
				.perform(
						get(BASE_URL + "/me/questions/reviewers")
								.param("question_id", "1")
								.header("Authorization", "{{accessToken")
								.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful())
				.andDo(
						document(
								"ViewReviewerList",
								resource(
										ResourceSnippetParameters.builder()
												.description("리뷰를 달아준 리뷰어 목록 조회")
												.tag(TAG)
												.requestHeaders(
														headerWithName("Authorization").description("{{accessToken}}"))
												.requestParameters(
														parameterWithName("question_id")
																.description("해당 글에 리뷰를 단 리뷰어 목록을 알기 위한 질문 글 id"))
												.responseSchema(Schema.schema("ViewQuestionRequestListResponse"))
												.responseFields(Description.success(MemberDescription.reviewerList()))
												.build())));
	}
}
