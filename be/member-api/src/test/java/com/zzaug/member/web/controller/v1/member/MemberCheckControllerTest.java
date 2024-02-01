package com.zzaug.member.web.controller.v1.member;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.parameterWithName;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzaug.member.MemberApp;
import com.zzaug.member.web.controller.v1.description.Description;
import com.zzaug.member.web.dto.member.CheckEmailAuthRequest;
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
@SpringBootTest(classes = MemberApp.class)
class MemberCheckControllerTest {
	@Autowired private MockMvc mockMvc;
	@Autowired private ObjectMapper objectMapper;
	private static final String TAG = "MemberCheckController";
	private static final String BASE_URL = "/api/v1/members/check";

	private static final String POST_BASE_URL_DNAME = "[POST] " + BASE_URL;
	private static final String GET_BASE_URL_DNAME = "[GET] " + BASE_URL;
	private static final String PUT_BASE_URL_DNAME = "[PUT] " + BASE_URL;
	private static final String DELETE_BASE_URL_DNAME = "[DELETE] " + BASE_URL;

	@Test
	@DisplayName(GET_BASE_URL_DNAME)
	void check() throws Exception {
		// set service mock

		mockMvc
				.perform(
						get(BASE_URL, 0)
								.contentType(MediaType.APPLICATION_JSON)
								.param("certification", "sample")
								.header("X-ZZAUG-ID", "X-ZZAUG-ID")
								.header(HttpHeaders.REFERER, "referer")
								.header(HttpHeaders.AUTHORIZATION, "Bearer accessToken"))
				.andExpect(status().is2xxSuccessful())
				.andDo(
						document(
								"CheckMember",
								resource(
										ResourceSnippetParameters.builder()
												.description("아이디 중복 검사를 진행합니다.")
												.tag(TAG)
												.requestSchema(Schema.schema("CheckMemberRequest"))
												.requestHeaders(
														Description.authHeader(),
														Description.xZzaugIdHeader(),
														Description.refererHeader())
												.requestParameters(
														parameterWithName("certification").description("증명(아이디)"))
												.responseSchema(Schema.schema("CheckMemberResponse"))
												.responseFields(
														Description.success(
																new FieldDescriptor[] {
																	fieldWithPath("data")
																			.type(JsonFieldType.OBJECT)
																			.description("data"),
																	fieldWithPath("data.duplication")
																			.type(JsonFieldType.BOOLEAN)
																			.description("요청 증명(아이디) 중복 여부"),
																}))
												.build())));
	}

	@Test
	@DisplayName(GET_BASE_URL_DNAME + "/email")
	void emailAuth() throws Exception {
		// set service mock

		mockMvc
				.perform(
						get(BASE_URL + "/email", 0)
								.contentType(MediaType.APPLICATION_JSON)
								.param("email", "{email}")
								.param("nonce", "{nonce}")
								.header("X-ZZAUG-ID", "X-ZZAUG-ID")
								.header(HttpHeaders.REFERER, "referer")
								.header(HttpHeaders.AUTHORIZATION, "Bearer accessToken"))
				.andExpect(status().is2xxSuccessful())
				.andDo(
						document(
								"EmailAuth",
								resource(
										ResourceSnippetParameters.builder()
												.description("이메일 인증 요청을 진행합니다.")
												.tag(TAG)
												.requestSchema(Schema.schema("EmailAuthRequest"))
												.requestHeaders(
														Description.authHeader(),
														Description.xZzaugIdHeader(),
														Description.refererHeader())
												.requestParameters(
														parameterWithName("email").description("이메일"),
														parameterWithName("nonce").description("인증번호"))
												.responseSchema(Schema.schema("EmailAuthResponse"))
												.responseFields(
														Description.success(
																new FieldDescriptor[] {
																	fieldWithPath("data")
																			.type(JsonFieldType.OBJECT)
																			.description("data"),
																	fieldWithPath("data.duplication")
																			.type(JsonFieldType.BOOLEAN)
																			.description("요청 이메일 중복 여부"),
																}))
												.build())));
	}

	@Test
	@DisplayName(POST_BASE_URL_DNAME + "/email")
	void checkEmailAuth() throws Exception {
		// set service mock
		CheckEmailAuthRequest request =
				CheckEmailAuthRequest.builder().code("code").email("email").nonce("nonce").build();
		String content = objectMapper.writeValueAsString(request);

		mockMvc
				.perform(
						post(BASE_URL + "/email", 0)
								.contentType(MediaType.APPLICATION_JSON)
								.content(content)
								.header("X-ZZAUG-ID", "X-ZZAUG-ID")
								.header(HttpHeaders.REFERER, "referer")
								.header(HttpHeaders.AUTHORIZATION, "Bearer accessToken"))
				.andExpect(status().is2xxSuccessful())
				.andDo(
						document(
								"CheckEmailAuth",
								resource(
										ResourceSnippetParameters.builder()
												.description("이메일 인증 번호 확인합니다.")
												.tag(TAG)
												.requestSchema(Schema.schema("CheckEmailAuthRequest"))
												.requestHeaders(
														Description.authHeader(),
														Description.xZzaugIdHeader(),
														Description.refererHeader())
												.responseSchema(Schema.schema("CheckEmailAuthResponse"))
												.responseFields(
														Description.success(
																new FieldDescriptor[] {
																	fieldWithPath("data")
																			.type(JsonFieldType.OBJECT)
																			.description("data"),
																	fieldWithPath("data.authentication")
																			.type(JsonFieldType.BOOLEAN)
																			.description("인증 번호 확인 결과"),
																	fieldWithPath("data.tryCount")
																			.type(JsonFieldType.NUMBER)
																			.description("인증 번호 확인 시도 횟수"),
																}))
												.build())));
	}
}
