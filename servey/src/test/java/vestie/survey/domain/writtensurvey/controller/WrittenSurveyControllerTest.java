package vestie.survey.domain.writtensurvey.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import vestie.survey.domain.auth.AuthMember;
import vestie.survey.domain.writtensurvey.controller.request.ChoiceQuestionAnswerRequest;
import vestie.survey.domain.writtensurvey.controller.request.SubjectiveQuestionAnswerRequest;
import vestie.survey.domain.writtensurvey.controller.request.WrittenSurveyRequest;
import vestie.survey.domain.writtensurvey.service.WrittenSurveyService;
import vestie.survey.fixture.WrittenSurveyFixture;
import vestie.survey.global.config.web.WebConfig;
import vestie.survey.global.exception.FailedAuthenticationException;
import vestie.survey.global.web.argumentresolver.auth.AuthMemberArgumentResolver;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static vestie.survey.fixture.AnswerFixture.choiceQuestionAnswerRequest;

/**
 * Created by ShinD on 2022/10/14.
 */

@WebMvcTest(WrittenSurveyController.class)
@Import({ WebConfig.class})
class WrittenSurveyControllerTest {

	@Autowired
	private MockMvc mockMvc;

	private final ObjectMapper objectMapper = new ObjectMapper();

	@MockBean
	private WrittenSurveyService writtenSurveyService;

	@MockBean
	private AuthMemberArgumentResolver authMemberArgumentResolver;

	@Test
	@DisplayName("[API] [POST] ?????? ?????? ???????????? ?????? ?????????")
	public void successRespondSurvey() throws Exception {

		//given
		WrittenSurveyRequest request = WrittenSurveyFixture.writtenSurveyRequest();
		Long memberId = 10L;
		Long savedId = 12L;
		when(writtenSurveyService.save(request.toServiceDto(memberId))).thenReturn(savedId);
		when(authMemberArgumentResolver.resolveArgument(any(), any(), any(), any())).thenReturn(new AuthMember(memberId, "sample"));
		when(authMemberArgumentResolver.supportsParameter(any())).thenReturn(true);

		//when
		MvcResult mvcResult = mockMvc.perform(
				post("/api/v1/written-survey")
					.contentType(APPLICATION_JSON)
					.content(objectMapper.writeValueAsBytes(request))
			)
			.andExpect(status().isCreated())
			.andReturn();

		//then
		assertThat(mvcResult.getResponse().getHeader("location"))
			.isEqualTo("/v1/written-survey/%d".formatted(savedId));
	}

	@Test
	@DisplayName("[API] [POST] ?????? ?????? ?????? - ?????? ????????? ????????? ??????")
	public void failRespondSurveyCauseNoAuth() throws Exception {

		//given
		WrittenSurveyRequest request = WrittenSurveyFixture.writtenSurveyRequest();
		Long memberId = 10L;
		Long savedId = 12L;
		when(writtenSurveyService.save(request.toServiceDto(memberId))).thenReturn(savedId);
		when(authMemberArgumentResolver.resolveArgument(any(), any(), any(), any())).thenThrow(new FailedAuthenticationException("Auth Token??? ?????? ????????? ??? ????????????."));
		when(authMemberArgumentResolver.supportsParameter(any())).thenReturn(true);

		//when
		mockMvc.perform(
				post("/api/v1/written-survey")
					.contentType(APPLICATION_JSON)
					.content(objectMapper.writeValueAsBytes(request))
			)
			.andExpect(status().isUnauthorized());


		// then
		verify(writtenSurveyService, times(0)).save(any());
	}

	@Test
	@DisplayName("[API] [POST] ?????? ?????? ?????? - ????????? ????????? 0?????? ??????")
	public void failRespondSurveyCauseEmptyRequestValue() throws Exception {
		//given
		WrittenSurveyRequest request = WrittenSurveyFixture.writtenSurveyRequest(new ArrayList<>(), new ArrayList<>());
		Long memberId = 10L;
		Long savedId = 12L;
		when(writtenSurveyService.save(any())).thenReturn(savedId);
		when(authMemberArgumentResolver.resolveArgument(any(), any(), any(), any())).thenReturn(new AuthMember(memberId, "sample"));
		when(authMemberArgumentResolver.supportsParameter(any())).thenReturn(true);

		//when
		mockMvc.perform(
				post("/api/v1/written-survey")
					.contentType(APPLICATION_JSON)
					.content(objectMapper.writeValueAsBytes(request))
			)
			.andExpect(status().isBadRequest());


		// then
		verify(writtenSurveyService, times(0)).save(any());
	}

	@Test
	@DisplayName("[API] [POST] ?????? ?????? ?????? - ???????????? ????????? ?????? ??????(EX ?????? ??????)")
	public void failRespondSurveyCauseEmptyRequestValue2() throws Exception {
		//given
		WrittenSurveyRequest request = WrittenSurveyFixture.emptyWrittenSurveyRequest();
		request.subjectiveQuestionAnswerRequests().add(new SubjectiveQuestionAnswerRequest(1L, "!"));
		Long memberId = 10L;
		Long savedId = 12L;
		when(writtenSurveyService.save(any())).thenReturn(savedId);
		when(authMemberArgumentResolver.resolveArgument(any(), any(), any(), any())).thenReturn(new AuthMember(memberId, "sample"));
		when(authMemberArgumentResolver.supportsParameter(any())).thenReturn(true);

		//when
		mockMvc.perform(
				post("/api/v1/written-survey")
					.contentType(APPLICATION_JSON)
					.content(objectMapper.writeValueAsBytes(request))
			)
			.andExpect(status().isBadRequest());


		// then
		verify(writtenSurveyService, times(0)).save(any());
	}

	@Test
	@DisplayName("[API] [POST] ?????? ?????? ?????? - ????????? ????????? ?????? ????????? ???????????? ?????? ??????")
	public void failRespondSurveyCauseEmptyRequestValue3() throws Exception {
		//given
		WrittenSurveyRequest request = WrittenSurveyFixture.writtenSurveyRequest();
		request.choiceQuestionAnswerRequests().add(new ChoiceQuestionAnswerRequest(1L, List.of()));
		Long memberId = 10L;
		Long savedId = 12L;
		when(writtenSurveyService.save(any())).thenReturn(savedId);
		when(authMemberArgumentResolver.resolveArgument(any(), any(), any(), any())).thenReturn(new AuthMember(memberId, "sample"));
		when(authMemberArgumentResolver.supportsParameter(any())).thenReturn(true);

		//when
		mockMvc.perform(
				post("/api/v1/written-survey")
					.contentType(APPLICATION_JSON)
					.content(objectMapper.writeValueAsBytes(request))
			)
			.andExpect(status().isBadRequest());

		// then
		verify(writtenSurveyService, times(0)).save(any());
	}

	@Test
	@DisplayName("[API] [POST] ?????? ?????? ?????? - ????????? ????????? MAN, WOMAN??? ?????? ??????")
	public void failRespondSurveyCauseWriterGender() throws Exception {
		//given
		ChoiceQuestionAnswerRequest answerRequest = choiceQuestionAnswerRequest(1L, 2L);
		WrittenSurveyRequest request = new WrittenSurveyRequest(12, null, 1L, List.of(answerRequest), new ArrayList<>());
		Long memberId = 10L;
		Long savedId = 12L;
		when(writtenSurveyService.save(request.toServiceDto(memberId))).thenReturn(savedId);
		when(authMemberArgumentResolver.resolveArgument(any(), any(), any(), any())).thenReturn(new AuthMember(memberId, "sample"));
		when(authMemberArgumentResolver.supportsParameter(any())).thenReturn(true);

		//when
		mockMvc.perform(
				post("/api/v1/written-survey")
					.contentType(APPLICATION_JSON)
					.content(objectMapper.writeValueAsBytes(request))
			)
			.andExpect(status().isBadRequest());

		// then
		verify(writtenSurveyService, times(0)).save(any());
	}
}