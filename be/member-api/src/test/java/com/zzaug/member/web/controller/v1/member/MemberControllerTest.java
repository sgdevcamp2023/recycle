package com.zzaug.member.web.controller.v1.member;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.parameterWithName;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epages.restdocs.apispec.HeaderDescriptorWithType;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzaug.member.MemberApp;
import com.zzaug.member.domain.dto.member.GetMemberUseCaseResponse;
import com.zzaug.member.domain.dto.member.MemberAuthToken;
import com.zzaug.member.domain.dto.member.PostMemberUseCaseResponse;
import com.zzaug.member.domain.dto.member.SearchMemberUseCaseResponse;
import com.zzaug.member.domain.dto.member.UpdateMemberUseCaseResponse;
import com.zzaug.member.domain.usecase.member.DeleteMemberUseCase;
import com.zzaug.member.domain.usecase.member.GetMemberUseCase;
import com.zzaug.member.domain.usecase.member.LogOutUseCase;
import com.zzaug.member.domain.usecase.member.LoginUseCase;
import com.zzaug.member.domain.usecase.member.PostMemberUseCase;
import com.zzaug.member.domain.usecase.member.RenewalTokenUseCase;
import com.zzaug.member.domain.usecase.member.SearchMemberUseCase;
import com.zzaug.member.domain.usecase.member.UpdateMemberUseCase;
import com.zzaug.member.web.controller.config.TestTokenUserDetailsService;
import com.zzaug.member.web.controller.v1.description.Description;
import com.zzaug.member.web.dto.member.LoginRequest;
import com.zzaug.member.web.dto.member.MemberSaveRequest;
import com.zzaug.member.web.dto.member.MemberUpdateRequest;
import java.util.UUID;
import javax.servlet.http.Cookie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@ActiveProfiles(value = "test")
@AutoConfigureRestDocs
@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest(classes = {MemberApp.class, TestTokenUserDetailsService.class})
class MemberControllerTest {
	@Autowired private MockMvc mockMvc;
	@Autowired private ObjectMapper objectMapper;

	@MockBean GetMemberUseCase getMemberUseCase;
	@MockBean UpdateMemberUseCase updateMemberUseCase;
	@MockBean PostMemberUseCase postMemberUseCase;
	@MockBean DeleteMemberUseCase deleteMemberUseCase;
	@MockBean LoginUseCase loginUseCase;
	@MockBean LogOutUseCase logOutUseCase;
	@MockBean RenewalTokenUseCase renewalTokenUseCase;
	@MockBean SearchMemberUseCase searchMemberUseCase;

	@Value("${token.test}")
	private String testToken;

	private static final String TAG = "MemberController";
	private static final String BASE_URL = "/api/v1/members";

	private static final String POST_BASE_URL_DNAME = "[POST] " + BASE_URL;
	private static final String GET_BASE_URL_DNAME = "[GET] " + BASE_URL;
	private static final String PUT_BASE_URL_DNAME = "[PUT] " + BASE_URL;
	private static final String DELETE_BASE_URL_DNAME = "[DELETE] " + BASE_URL;

	private static final String X_ZZAUG_ID = "X-ZZAUG-ID";
	private static final String CERTIFICATION = "certification";
	private static final String PASSWORD = "password@123";

	@Test
	@DisplayName(POST_BASE_URL_DNAME)
	@WithUserDetails(userDetailsServiceBeanName = "testTokenUserDetailsService")
	void save() throws Exception {
		// set service mock
		MemberSaveRequest request =
				MemberSaveRequest.builder().certification(CERTIFICATION).password(PASSWORD).build();
		String content = objectMapper.writeValueAsString(request);

		when(postMemberUseCase.execute(any()))
				.thenReturn(
						PostMemberUseCaseResponse.builder()
								.memberId(1L)
								.certification(CERTIFICATION)
								.password(PASSWORD)
								.build());

		mockMvc
				.perform(
						post(BASE_URL, 0)
								.contentType(MediaType.APPLICATION_JSON)
								.content(content)
								.header(X_ZZAUG_ID, UUID.randomUUID())
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
												.requestHeaders(Description.authHeader(), Description.xZzaugIdHeader())
												.responseSchema(Schema.schema("SaveMemberResponse"))
												.responseFields(Description.success())
												.build())));
	}

	@Test
	@DisplayName(PUT_BASE_URL_DNAME)
	@WithUserDetails(userDetailsServiceBeanName = "testTokenUserDetailsService")
	void update() throws Exception {
		// set service mock
		MemberUpdateRequest request =
				MemberUpdateRequest.builder().certification(CERTIFICATION).password(PASSWORD).build();
		String content = objectMapper.writeValueAsString(request);
		Cookie cookie = new Cookie("refreshToken", testToken);

		when(updateMemberUseCase.execute(any()))
				.thenReturn(
						UpdateMemberUseCaseResponse.builder()
								.accessToken(testToken)
								.refreshToken(testToken)
								.build());

		mockMvc
				.perform(
						put(BASE_URL, 0)
								.contentType(MediaType.APPLICATION_JSON)
								.content(content)
								.header(X_ZZAUG_ID, UUID.randomUUID())
								.header(HttpHeaders.AUTHORIZATION, "Bearer accessToken")
								.cookie(cookie))
				.andExpect(status().is2xxSuccessful())
				.andDo(
						document(
								"UpdateMember",
								resource(
										ResourceSnippetParameters.builder()
												.description("회원정보를 수정합니다.")
												.tag(TAG)
												.requestSchema(Schema.schema("UpdateMemberRequest"))
												.requestHeaders(Description.authHeader(), Description.xZzaugIdHeader())
												.responseSchema(Schema.schema("UpdateMemberResponse"))
												.responseFields(
														Description.success(
																new FieldDescriptor[] {
																	fieldWithPath("data")
																			.type(JsonFieldType.OBJECT)
																			.description("data"),
																	fieldWithPath("data.accessToken")
																			.type(JsonFieldType.STRING)
																			.description("어세스 토큰"),
																}))
												.build())));
	}

	@Test
	@DisplayName(DELETE_BASE_URL_DNAME)
	@WithUserDetails(userDetailsServiceBeanName = "testTokenUserDetailsService")
	void delete() throws Exception {
		// set service mock
		Cookie cookie = new Cookie("refreshToken", testToken);

		mockMvc
				.perform(
						RestDocumentationRequestBuilders.delete(BASE_URL, 0)
								.contentType(MediaType.APPLICATION_JSON)
								.header(X_ZZAUG_ID, UUID.randomUUID())
								.header(HttpHeaders.AUTHORIZATION, "Bearer accessToken")
								.cookie(cookie))
				.andExpect(status().is2xxSuccessful())
				.andDo(
						document(
								"UpdateMember",
								resource(
										ResourceSnippetParameters.builder()
												.description("회원 탈퇴를 진행합니다.")
												.tag(TAG)
												.requestSchema(Schema.schema("UpdateMemberRequest"))
												.requestHeaders(Description.authHeader(), Description.xZzaugIdHeader())
												.responseSchema(Schema.schema("UpdateMemberResponse"))
												.responseFields(Description.deleted())
												.build())));
	}

	@Test
	@DisplayName(POST_BASE_URL_DNAME + "/login")
	@WithUserDetails(userDetailsServiceBeanName = "testTokenUserDetailsService")
	void login() throws Exception {
		// set service mock
		LoginRequest request =
				LoginRequest.builder().certification(CERTIFICATION).password(PASSWORD).build();
		String content = objectMapper.writeValueAsString(request);

		when(loginUseCase.execute(any()))
				.thenReturn(
						MemberAuthToken.builder().accessToken(testToken).refreshToken(testToken).build());

		mockMvc
				.perform(
						post(BASE_URL + "/login", 0)
								.contentType(MediaType.APPLICATION_JSON)
								.content(content)
								.header(X_ZZAUG_ID, UUID.randomUUID())
								.header(HttpHeaders.AUTHORIZATION, "Bearer accessToken")
								.header(HttpHeaders.USER_AGENT, "agent"))
				.andExpect(status().is2xxSuccessful())
				.andDo(
						document(
								"LoginMember",
								resource(
										ResourceSnippetParameters.builder()
												.description("로그인을 진행합니다.")
												.tag(TAG)
												.requestSchema(Schema.schema("LoginMemberRequest"))
												.requestHeaders(Description.authHeader(), Description.xZzaugIdHeader())
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
																}))
												.responseHeaders(
														new HeaderDescriptorWithType[] {Description.setCookieHeader()})
												.build())));
	}

	@Test
	@DisplayName(POST_BASE_URL_DNAME + "/logout")
	@WithUserDetails(userDetailsServiceBeanName = "testTokenUserDetailsService")
	void logout() throws Exception {
		// set service mock
		Cookie cookie = new Cookie("refreshToken", testToken);

		mockMvc
				.perform(
						post(BASE_URL + "/logout", 0)
								.contentType(MediaType.APPLICATION_JSON)
								.header(X_ZZAUG_ID, UUID.randomUUID())
								.header(HttpHeaders.AUTHORIZATION, "Bearer accessToken")
								.cookie(cookie))
				.andExpect(status().is2xxSuccessful())
				.andDo(
						document(
								"LogoutMember",
								resource(
										ResourceSnippetParameters.builder()
												.description("로그아웃을 진행합니다.")
												.tag(TAG)
												.requestSchema(Schema.schema("LogoutMemberRequest"))
												.requestHeaders(Description.authHeader(), Description.xZzaugIdHeader())
												.responseSchema(Schema.schema("LogoutMemberResponse"))
												.responseFields(Description.success())
												.build())));
	}

	@Test
	@DisplayName(GET_BASE_URL_DNAME + "/{id}")
	@WithUserDetails(userDetailsServiceBeanName = "testTokenUserDetailsService")
	void read() throws Exception {
		// set service mock
		when(getMemberUseCase.execute(any()))
				.thenReturn(
						GetMemberUseCaseResponse.builder()
								.id(1L)
								.email("sample@email.com")
								.github("github")
								.build());

		mockMvc
				.perform(
						get(BASE_URL + "/{id}", 1)
								.contentType(MediaType.APPLICATION_JSON)
								.header(X_ZZAUG_ID, UUID.randomUUID())
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
												.requestHeaders(Description.authHeader(), Description.xZzaugIdHeader())
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
	@WithUserDetails(userDetailsServiceBeanName = "testTokenUserDetailsService")
	void search() throws Exception {
		// set service mock
		when(searchMemberUseCase.execute(any()))
				.thenReturn(
						SearchMemberUseCaseResponse.builder()
								.id(1L)
								.email("sample@email.com")
								.github("github")
								.build());

		mockMvc
				.perform(
						get(BASE_URL, 0)
								.contentType(MediaType.APPLICATION_JSON)
								.queryParam("certification", CERTIFICATION)
								.header(X_ZZAUG_ID, UUID.randomUUID())
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
												.requestHeaders(Description.authHeader(), Description.xZzaugIdHeader())
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
	@WithUserDetails(userDetailsServiceBeanName = "testTokenUserDetailsService")
	void token() throws Exception {
		// set service mock
		Cookie cookie = new Cookie("refreshToken", testToken);

		when(renewalTokenUseCase.execute(any()))
				.thenReturn(
						MemberAuthToken.builder().accessToken(testToken).refreshToken(testToken).build());

		mockMvc
				.perform(
						post(BASE_URL + "/token", 0)
								.contentType(MediaType.APPLICATION_JSON)
								.header(X_ZZAUG_ID, UUID.randomUUID())
								.header(HttpHeaders.AUTHORIZATION, "Bearer accessToken")
								.cookie(cookie))
				.andExpect(status().is2xxSuccessful())
				.andDo(
						document(
								"MemberToken",
								resource(
										ResourceSnippetParameters.builder()
												.description("토큰을 갱신합니다.")
												.tag(TAG)
												.requestSchema(Schema.schema("MemberTokenRequest"))
												.requestHeaders(Description.authHeader(), Description.xZzaugIdHeader())
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
																}))
												.responseHeaders(
														new HeaderDescriptorWithType[] {Description.setCookieHeader()})
												.build())));
	}
}
