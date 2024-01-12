package com.zzaug.review.web.controller.v1;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.*;
import static com.epages.restdocs.apispec.ResourceDocumentation.parameterWithName;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import com.epages.restdocs.apispec.SimpleType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzaug.review.ReviewApp;
import com.zzaug.review.web.controller.v1.description.Description;
import com.zzaug.review.web.controller.v1.description.ReviewDescription;
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
class ReviewQueryControllerTest {
	@Autowired private MockMvc mockMvc;
	@Autowired private ObjectMapper objectMapper;
	private static final String TAG = "QuestionQueryController";
	private static final String BASE_URL = "/api/v1";

	@Test
	@DisplayName("[GET] " + BASE_URL + "/question-query/{question_id}/reviews")
	void viewQuestionReviewList() throws Exception {
		mockMvc
				.perform(
						get(BASE_URL + "/question-query/{question_id}/reviews", 1)
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
	@DisplayName("[GET] " + BASE_URL + "/question-query/{question_id}/reviews/temp")
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
}
