package com.zzaug.notification.web.controller;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzaug.notification.NotificationApp;
import com.zzaug.notification.web.controller.description.Description;
import com.zzaug.notification.web.dto.notification.RequestReviewRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@ActiveProfiles(value = "test")
@AutoConfigureRestDocs
@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest(classes = NotificationApp.class)
class NotificationControllerTest {
	@Autowired private MockMvc mockMvc;
	@Autowired private ObjectMapper objectMapper;
	private static final String TAG = "NotificationController";
	private static final String BASE_URL = "/api/v1/notifications";

	private static final String GET_BASE_URL_NAME = "[GET] /api/v1/notifications";
	private static final String POST_BASE_URL_NAME = "[POST] /api/v1/notifications";
	private static final String PUT_BASE_URL_NAME = "[PUT] /api/v1/notifications";
	private static final String DELETE_BASE_URL_NAME = "[DELETE] /api/v1/notifications";

	@Test
	@DisplayName(POST_BASE_URL_NAME + "/request/reviews")
	void requestReview() throws Exception {
		// set service mock
		RequestReviewRequest request =
				RequestReviewRequest.builder().questionId(1L).requestMemberId(1L).build();
		String content = objectMapper.writeValueAsString(request);

		mockMvc
				.perform(
						post(BASE_URL + "/request/reviews", 0)
								.contentType(MediaType.APPLICATION_JSON)
								.content(content)
								.header("X-ZZAUG-ID", "X-ZZAUG-ID")
								.header(HttpHeaders.REFERER, "referer")
								.header(HttpHeaders.AUTHORIZATION, "Bearer accessToken"))
				.andExpect(status().is2xxSuccessful())
				.andDo(
						document(
								"RequestReview",
								resource(
										ResourceSnippetParameters.builder()
												.description("다른 멤버에게 리뷰를 요청합니다.")
												.tag(TAG)
												.requestSchema(Schema.schema("RequestReviewRequest"))
												.requestHeaders(
														Description.authHeader(),
														Description.xZzaugIdHeader(),
														Description.refererHeader())
												.responseSchema(Schema.schema("RequestReviewResponse"))
												.responseFields(Description.success())
												.build())));
	}

	@Test
	@DisplayName(GET_BASE_URL_NAME)
	void browse() throws Exception {
		// set service mock

		mockMvc
				.perform(
						get(BASE_URL, 0)
								.contentType(MediaType.APPLICATION_JSON)
								.header("X-ZZAUG-ID", "X-ZZAUG-ID")
								.header(HttpHeaders.REFERER, "referer")
								.header(HttpHeaders.AUTHORIZATION, "Bearer accessToken"))
				.andExpect(status().is2xxSuccessful())
				.andDo(
						document(
								"RequestReview",
								resource(
										ResourceSnippetParameters.builder()
												.description("다른 멤버에게 리뷰를 요청합니다.")
												.tag(TAG)
												.requestSchema(Schema.schema("RequestReviewRequest"))
												.requestHeaders(
														Description.authHeader(),
														Description.xZzaugIdHeader(),
														Description.refererHeader())
												.responseSchema(Schema.schema("RequestReviewResponse"))
												.responseFields(
														Description.success(
																new FieldDescriptor[] {
																	fieldWithPath("data")
																			.type(JsonFieldType.OBJECT)
																			.description("리뷰 요청 알림"),
																	fieldWithPath("data.notifications[]")
																			.type(JsonFieldType.ARRAY)
																			.description("리뷰 요청 알림"),
																	fieldWithPath("data.notifications[].type")
																			.type(JsonFieldType.STRING)
																			.description("알림 타입"),
																	fieldWithPath("data.notifications[].title")
																			.type(JsonFieldType.STRING)
																			.description("알림 제목"),
																	fieldWithPath("data.notifications[].content")
																			.type(JsonFieldType.STRING)
																			.description("알림 내용"),
																	fieldWithPath("data.notifications[].notice_at")
																			.type(JsonFieldType.STRING)
																			.description("알림 시간"),
																}))
												.build())));
	}
}
