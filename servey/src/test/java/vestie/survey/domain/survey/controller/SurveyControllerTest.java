package vestie.survey.domain.survey.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import vestie.survey.domain.auth.AuthMember;
import vestie.survey.domain.survey.controller.request.SurveyRequest;
import vestie.survey.domain.survey.controller.response.SurveyCompleteInfoResponse;
import vestie.survey.domain.survey.controller.response.SurveySimpleResponse;
import vestie.survey.domain.survey.service.SurveyQueryService;
import vestie.survey.domain.survey.service.SurveyService;
import vestie.survey.fixture.SurveyFixture;
import vestie.survey.global.config.web.WebConfig;
import vestie.survey.global.web.argumentresolver.auth.AuthMemberArgumentResolver;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static vestie.survey.fixture.SurveyFixture.surveyCompleteInfoResponse;
import static vestie.survey.fixture.SurveyFixture.surveySimpleResponse;

@WebMvcTest(SurveyController.class)
@Import({WebConfig.class})
class SurveyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @MockBean
    private SurveyService surveyService;

    @MockBean
    private SurveyQueryService surveyQueryService;

    @MockBean
    private AuthMemberArgumentResolver authMemberArgumentResolver;


    @Test
    @DisplayName("[API] [POST] 설문 등록 일반적인 성공 케이스")
    void successRegisterSurvey() throws Exception {
        // given
        SurveyRequest surveyRequest = SurveyFixture.surveyRequest();
        Long memberId = 10L;
        Long savedId = 12L;
        when(surveyService.save(any())).thenReturn(savedId);
        when(authMemberArgumentResolver.resolveArgument(any(), any(), any(), any())).thenReturn(new AuthMember(memberId, "sample"));
        when(authMemberArgumentResolver.supportsParameter(any())).thenReturn(true);

        //when
        MvcResult mvcResult = mockMvc.perform(
                        post("/api/v1/survey")
                                .contentType(APPLICATION_JSON)
                                .content(objectMapper.writeValueAsBytes(surveyRequest))
                )
                .andExpect(status().isCreated())
                .andReturn();

        //then
        assertThat(mvcResult.getResponse().getHeader("Location"))
                .isEqualTo("/v1/survey/%d".formatted(savedId));
    }

    @Test
    @DisplayName("[API] [GET] 설문 리스트 조회 일반적인 성공 케이스")
    void successGetAllSurveySimpleInfo() throws Exception {

        // given
        int size = 3;
        when(surveyQueryService.getAllSimpleInfo()).thenReturn(surveySimpleResponse(size));

        //when
        MvcResult mvcResult = mockMvc
                .perform(get("/api/v1/survey"))
                .andExpect(status().isOk())
                .andReturn();

        //then
        String contentAsString = mvcResult.getResponse().getContentAsString();

        List<SurveySimpleResponse> responses =
                objectMapper.readValue(contentAsString, new TypeReference<List<SurveySimpleResponse>>() {});

        assertThat(responses.size()).isEqualTo(size);
    }

    @Test
    @DisplayName("[API] [GET] 설문 작성을 위한 단일 조회 일반적인 성공 케이스")
    void successGetSurveyCompleteInfoById() throws Exception {

        // given
        long targetId = 1L;
        SurveyCompleteInfoResponse response = surveyCompleteInfoResponse(targetId);
        when(surveyQueryService.getSurveyCompleteInfoById(targetId)).thenReturn(response);

        //when
        MvcResult mvcResult = mockMvc
                .perform(get("/api/v1/survey/{targetId}",targetId))
                .andExpect(status().isOk())
                .andReturn();

        //then
        String contentAsString = mvcResult.getResponse().getContentAsString();

        SurveyCompleteInfoResponse responses =
                objectMapper.readValue(contentAsString, SurveyCompleteInfoResponse.class);

        assertThat(responses).isEqualTo(response);
    }
}