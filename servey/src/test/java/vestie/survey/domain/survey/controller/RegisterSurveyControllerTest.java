package vestie.survey.domain.survey.controller;

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
import vestie.survey.domain.auth.AuthMember;
import vestie.survey.domain.survey.controller.request.SurveyRequest;
import vestie.survey.domain.survey.service.SurveyService;
import vestie.survey.fixture.SurveyFixture;
import vestie.survey.global.config.web.WebConfig;
import vestie.survey.global.web.argumentresolver.auth.AuthMemberArgumentResolver;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RegisterSurveyController.class)
@Import({WebConfig.class})
class RegisterSurveyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private SurveyService surveyService;

    @MockBean
    private AuthMemberArgumentResolver authMemberArgumentResolver;


    @Test
    @DisplayName("[API] [POST] 설문 등록 일반적인 성공 케이스")
    void successRespondSurvey() throws Exception {
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
                                .content(objectMapper.registerModule(new JavaTimeModule()).writeValueAsBytes(surveyRequest))
                )
                .andExpect(status().isCreated())
                .andReturn();


        //then
        assertThat(mvcResult.getResponse().getHeader("Location"))
                .isEqualTo("/v1/survey/%d".formatted(savedId));
    }



}