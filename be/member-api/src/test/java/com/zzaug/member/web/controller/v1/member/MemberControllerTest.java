package com.zzaug.member.web.controller.v1.member;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.parameterWithName;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzaug.member.MemberApp;
import com.zzaug.member.web.controller.v1.description.Description;
import com.zzaug.member.web.dto.member.LoginRequest;
import com.zzaug.member.web.dto.member.MemberSaveRequest;
import com.zzaug.member.web.dto.member.MemberUpdateRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@ActiveProfiles(value = "test")
@AutoConfigureRestDocs
@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest(classes = MemberApp.class)
class MemberControllerTest {
	@Autowired private MockMvc mockMvc;
	@Autowired private ObjectMapper objectMapper;
	private static final String TAG = "MemberController";
	private static final String BASE_URL = "/api/v1/members";

	private static final String POST_BASE_URL_DNAME = "[POST] " + BASE_URL;
	private static final String GET_BASE_URL_DNAME = "[GET] " + BASE_URL;
	private static final String PUT_BASE_URL_DNAME = "[PUT] " + BASE_URL;
	private static final String DELETE_BASE_URL_DNAME = "[DELETE] " + BASE_URL;

	@Test
	@DisplayName(POST_BASE_URL_DNAME)
	void save() throws Exception {
		// set service mock
		MemberSaveRequest request =
				MemberSaveRequest.builder().certification("certification").password("password").build();

		String content = objectMapper.writeValueAsString(request);

		mockMvc
				.perform(
						post(BASE_URL, 0)
								.contentType(MediaType.APPLICATION_JSON)
								.content(content)
								.header("X-ZZAUG-ID", "X-ZZAUG-ID")
								.header(HttpHeaders.REFERER, "referer")
								.header(HttpHeaders.AUTHORIZATION, "Bearer accessToken"))
				.andExpect(status().is2xxSuccessful())
				.andDo(
						document(
								"SaveMember",
								resource(
										ResourceSnippetParameters.builder()
												.description("회원가입을 진행합니다.")
												.tag(TAG)
												.requestSchema(Schema.schema("SaveMemberRequest"))
												.requestHeaders(
														Description.authHeader(),
														Description.xZzaugIdHeader(),
														Description.refererHeader())
												.responseSchema(Schema.schema("SaveMemberResponse"))
												.responseFields(Description.success())
												.build())));
	}

	@Test
	@DisplayName(PUT_BASE_URL_DNAME)
	void update() throws Exception {
		// set service mock
		MemberUpdateRequest request =
				MemberUpdateRequest.builder().certification("certification").password("password").build();

		String content = objectMapper.writeValueAsString(request);
		mockMvc
				.perform(
						put(BASE_URL, 0)
								.contentType(MediaType.APPLICATION_JSON)
								.content(content)
								.header("X-ZZAUG-ID", "X-ZZAUG-ID")
								.header(HttpHeaders.REFERER, "referer")
								.header(HttpHeaders.AUTHORIZATION, "Bearer accessToken"))
				.andExpect(status().is2xxSuccessful())
				.andDo(
						document(
								"UpdateMember",
								resource(
										ResourceSnippetParameters.builder()
												.description("회원정보를 수정합니다.")
												.tag(TAG)
												.requestSchema(Schema.schema("UpdateMemberRequest"))
												.requestHeaders(
														Description.authHeader(),
														Description.xZzaugIdHeader(),
														Description.refererHeader())
												.responseSchema(Schema.schema("UpdateMemberResponse"))
												.responseFields(Description.modified())
												.build())));
	}

	@Test
	@DisplayName(DELETE_BASE_URL_DNAME)
	void delete() throws Exception {
		// set service mock

		mockMvc
				.perform(
						RestDocumentationRequestBuilders.delete(BASE_URL, 0)
								.contentType(MediaType.APPLICATION_JSON)
								.header("X-ZZAUG-ID", "X-ZZAUG-ID")
								.header(HttpHeaders.REFERER, "referer")
								.header(HttpHeaders.AUTHORIZATION, "Bearer accessToken"))
				.andExpect(status().is2xxSuccessful())
				.andDo(
						document(
								"UpdateMember",
								resource(
										ResourceSnippetParameters.builder()
												.description("회원 탈퇴를 진행합니다.")
												.tag(TAG)
												.requestSchema(Schema.schema("UpdateMemberRequest"))
												.requestHeaders(
														Description.authHeader(),
														Description.xZzaugIdHeader(),
														Description.refererHeader())
												.responseSchema(Schema.schema("UpdateMemberResponse"))
												.responseFields(Description.deleted())
												.build())));
	}

	@Test
	@DisplayName(POST_BASE_URL_DNAME + "/login")
	void login() throws Exception {
		// set service mock
		LoginRequest request =
				LoginRequest.builder().certification("certification").password("password").build();

		String content = objectMapper.writeValueAsString(request);

		mockMvc
				.perform(
						post(BASE_URL + "/login", 0)
								.contentType(MediaType.APPLICATION_JSON)
								.content(content)
								.header("X-ZZAUG-ID", "X-ZZAUG-ID")
								.header(HttpHeaders.REFERER, "referer")
								.header(HttpHeaders.AUTHORIZATION, "Bearer accessToken"))
				.andExpect(status().is2xxSuccessful())
				.andDo(
						document(
								"LoginMember",
								resource(
										ResourceSnippetParameters.builder()
												.description("로그인을 진행합니다.")
												.tag(TAG)
												.requestSchema(Schema.schema("LoginMemberRequest"))
												.requestHeaders(
														Description.authHeader(),
														Description.xZzaugIdHeader(),
														Description.refererHeader())
												.responseSchema(Schema.schema("LoginMemberResponse"))
												.responseFields(
														Description.success(
																new FieldDescriptor[] {
																	fieldWithPath("data")
																			.type(JsonFieldType.OBJECT)
																			.description("data"),
																	fieldWithPath("data.accessToken")
																			.type(JsonFieldType.STRING)
																			.description("어세스 토큰"),
																	fieldWithPath("data.refreshToken")
																			.type(JsonFieldType.STRING)
																			.description("리프레시 토큰"),
																}))
												.build())));
	}

	@Test
	@DisplayName(POST_BASE_URL_DNAME + "/logout")
	void logout() throws Exception {
		// set service mock

		mockMvc
				.perform(
						post(BASE_URL + "/logout", 0)
								.contentType(MediaType.APPLICATION_JSON)
								.header("X-ZZAUG-ID", "X-ZZAUG-ID")
								.header(HttpHeaders.REFERER, "referer")
								.header(HttpHeaders.AUTHORIZATION, "Bearer accessToken"))
				.andExpect(status().is2xxSuccessful())
				.andDo(
						document(
								"LogoutMember",
								resource(
										ResourceSnippetParameters.builder()
												.description("로그아웃을 진행합니다.")
												.tag(TAG)
												.requestSchema(Schema.schema("LogoutMemberRequest"))
												.requestHeaders(
														Description.authHeader(),
														Description.xZzaugIdHeader(),
														Description.refererHeader())
												.responseSchema(Schema.schema("LogoutMemberResponse"))
												.responseFields(Description.success())
												.build())));
	}

	@Test
	@DisplayName(GET_BASE_URL_DNAME + "/{id}")
	void read() throws Exception {
		// set service mock

		mockMvc
				.perform(
						get(BASE_URL + "/{id}", 1)
								.contentType(MediaType.APPLICATION_JSON)
								.header("X-ZZAUG-ID", "X-ZZAUG-ID")
								.header(HttpHeaders.REFERER, "referer")
								.header(HttpHeaders.AUTHORIZATION, "Bearer accessToken"))
				.andExpect(status().is2xxSuccessful())
				.andDo(
						document(
								"ReadMember",
								resource(
										ResourceSnippetParameters.builder()
												.description("멤버 정보를 조회합니다.")
												.tag(TAG)
												.requestSchema(Schema.schema("ReadMemberRequest"))
												.requestHeaders(
														Description.authHeader(),
														Description.xZzaugIdHeader(),
														Description.refererHeader())
												.pathParameters(parameterWithName("id").description("조회할 멤버의 아이디"))
												.responseSchema(Schema.schema("ReadMemberResponse"))
												.responseFields(
														Description.success(
																new FieldDescriptor[] {
																	fieldWithPath("data")
																			.type(JsonFieldType.OBJECT)
																			.description("data"),
																	fieldWithPath("data.id")
																			.type(JsonFieldType.NUMBER)
																			.description("멤버 아이디"),
																	fieldWithPath("data.email")
																			.type(JsonFieldType.STRING)
																			.description("멤버 이메일"),
																	fieldWithPath("data.github")
																			.type(JsonFieldType.STRING)
																			.description("멤버 깃허브"),
																}))
												.build())));
	}

	@Test
	@DisplayName(GET_BASE_URL_DNAME)
	void search() throws Exception {
		// set service mock

		mockMvc
				.perform(
						get(BASE_URL, 0)
								.contentType(MediaType.APPLICATION_JSON)
								.queryParam("certification", "{certification}")
								.header("X-ZZAUG-ID", "X-ZZAUG-ID")
								.header(HttpHeaders.REFERER, "referer")
								.header(HttpHeaders.AUTHORIZATION, "Bearer accessToken"))
				.andExpect(status().is2xxSuccessful())
				.andDo(
						document(
								"SearchMember",
								resource(
										ResourceSnippetParameters.builder()
												.description("증명(아이디)를 조회합니다.")
												.requestParameters(
														parameterWithName("certification").description("증명(아이디)").optional())
												.tag(TAG)
												.requestSchema(Schema.schema("SearchMemberRequest"))
												.requestHeaders(
														Description.authHeader(),
														Description.xZzaugIdHeader(),
														Description.refererHeader())
												.responseSchema(Schema.schema("SearchMemberResponse"))
												.responseFields(
														Description.success(
																new FieldDescriptor[] {
																	fieldWithPath("data")
																			.type(JsonFieldType.OBJECT)
																			.description("data"),
																	fieldWithPath("data.id")
																			.type(JsonFieldType.NUMBER)
																			.description("멤버 아이디"),
																	fieldWithPath("data.email")
																			.type(JsonFieldType.STRING)
																			.description("멤버 이메일"),
																	fieldWithPath("data.github")
																			.type(JsonFieldType.STRING)
																			.description("멤버 깃허브"),
																}))
												.build())));
	}

	@Test
	@DisplayName(POST_BASE_URL_DNAME + "/token")
	void token() throws Exception {
		// set service mock

		mockMvc
				.perform(
						post(BASE_URL + "/token", 0)
								.contentType(MediaType.APPLICATION_JSON)
								.header("X-ZZAUG-ID", "X-ZZAUG-ID")
								.header(HttpHeaders.REFERER, "referer")
								.header(HttpHeaders.AUTHORIZATION, "Bearer accessToken"))
				.andExpect(status().is2xxSuccessful())
				.andDo(
						document(
								"MemberToken",
								resource(
										ResourceSnippetParameters.builder()
												.description("토큰을 갱신합니다.")
												.tag(TAG)
												.requestSchema(Schema.schema("MemberTokenRequest"))
												.requestHeaders(
														Description.authHeader(),
														Description.xZzaugIdHeader(),
														Description.refererHeader())
												.responseSchema(Schema.schema("MemberTokenResponse"))
												.responseFields(
														Description.success(
																new FieldDescriptor[] {
																	fieldWithPath("data")
																			.type(JsonFieldType.OBJECT)
																			.description("data"),
																	fieldWithPath("data.accessToken")
																			.type(JsonFieldType.STRING)
																			.description("어세스 토큰"),
																	fieldWithPath("data.refreshToken")
																			.type(JsonFieldType.STRING)
																			.description("리프레시 토큰"),
																}))
												.build())));
	}
}
