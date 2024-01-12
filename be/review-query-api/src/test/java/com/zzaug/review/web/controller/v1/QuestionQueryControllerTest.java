package com.zzaug.review.web.controller.v1;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import com.epages.restdocs.apispec.SimpleType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzaug.review.ReviewApp;
import com.zzaug.review.web.controller.v1.description.Description;
import com.zzaug.review.web.controller.v1.description.QuestionDescription;
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
import static com.epages.restdocs.apispec.ResourceDocumentation.parameterWithName;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ActiveProfiles(value = "test")
@AutoConfigureRestDocs
@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest(classes = ReviewApp.class)
public class QuestionQueryControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    private static final String TAG = "ReviewQueryController";
    private static final String BASE_URL = "/api/v1/question-query";

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
}
