package com.zzaug.review.web.controller.v1;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzaug.review.ReviewApp;
import com.zzaug.review.web.controller.v1.description.Description;
import com.zzaug.review.web.controller.v1.description.QuestionDescription;
import com.zzaug.review.web.controller.v1.description.ReviewDescription;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles(value = "test")
@AutoConfigureRestDocs
@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest(classes = ReviewApp.class)
public class MeQueryControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    private static final String TAG = "MeQueryController";
    private static final String BASE_URL = "/api/v1/me-query";

    @Test
    @DisplayName("[GET] " + BASE_URL + "/questions")
    void viewQuestionList() throws Exception {
        mockMvc
                .perform(
                        get(BASE_URL + "/questions")
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
    @DisplayName("[GET] " + BASE_URL + "/questions/temp")
    void viewTempQuestionList() throws Exception {
        mockMvc
                .perform(
                        get(BASE_URL + "/questions/temp")
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
    @DisplayName("[GET] " + BASE_URL + "/reviews")
    void viewMemberReviewList() throws Exception {
        mockMvc
                .perform(
                        get(BASE_URL + "/reviews")
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
    @DisplayName("[GET] " + BASE_URL + "/requests/reviews")
    void viewQuestionRequestList() throws Exception {
        mockMvc
                .perform(
                        get(BASE_URL + "/requests/reviews")
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
}
