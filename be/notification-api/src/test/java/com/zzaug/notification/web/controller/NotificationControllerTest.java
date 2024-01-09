package com.zzaug.notification.web.controller;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzaug.notification.NotificationApp;
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
@SpringBootTest(classes = NotificationApp.class)
class NotificationControllerTest {
	@Autowired private MockMvc mockMvc;
	@Autowired private ObjectMapper objectMapper;
	private static final String TAG = "NotificationController";
	private static final String BASE_URL = "/api/v1/notifications";

	@Test
	@DisplayName(BASE_URL)
	void test() throws Exception {
		// set service mock

		mockMvc
				.perform(post(BASE_URL, 0).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful())
				.andDo(
						document(
								"Notification",
								resource(
										ResourceSnippetParameters.builder()
												.description("Notification API")
												.tag(TAG)
												.requestSchema(Schema.schema("NotificationRequest"))
												.responseSchema(Schema.schema("NotificationResponse"))
												.responseFields(
														new FieldDescriptor[] {
															fieldWithPath("code").type(JsonFieldType.STRING).description("코드"),
															fieldWithPath("message")
																	.type(JsonFieldType.STRING)
																	.description("메시지"),
															fieldWithPath("data")
																	.type(JsonFieldType.OBJECT)
																	.description("Notification"),
															fieldWithPath("data.name")
																	.type(JsonFieldType.STRING)
																	.description("이름"),
														})
												.build())));
	}
}
