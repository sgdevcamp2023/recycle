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
								.header("Authorization", "{{accessToken}}")
								.content(content)
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
