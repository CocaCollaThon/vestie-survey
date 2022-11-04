package vestie.survey.domain.survey.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import vestie.survey.domain.survey.controller.response.SurveyCompleteInfoResponse;
import vestie.survey.domain.survey.controller.response.SurveySimpleResponse;
import vestie.survey.domain.survey.service.dto.SurveyDto;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static vestie.survey.fixture.SurveyFixture.surveyDto;

@DataJpaTest
@Import({SurveyQueryService.class, SurveyService.class})
@DisplayName("SurveyQueryService 는")
class SurveyQueryServiceTest {

    @Autowired
    private SurveyQueryService surveyQueryService;

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private EntityManager em;


    @Test
    @DisplayName("등록된 설문의 간단한 정보를 반환한다.")
    public void successGetAllSimpleInfo() throws Exception {
        // given
        SurveyDto surveyDto = surveyDto();
        surveyService.save(surveyDto);

        // when
        List<SurveySimpleResponse> simpleInfo = surveyQueryService.getAllSimpleInfo();

        // then
        assertThat(simpleInfo.size()).isEqualTo(1);
        assertThat(simpleInfo.get(0).title()).isEqualTo(surveyDto.getTitle());
    }

    @Test
    @DisplayName("설문 참여를 위한 설문의 상세한 정보를 반환한다.")
    public void successGetSurveyCompleteInfoById() throws Exception {
        // given
        SurveyDto surveyDto = surveyDto();
        Long id = surveyService.save(surveyDto);

        // when
        SurveyCompleteInfoResponse info = surveyQueryService.getSurveyCompleteInfoById(id);

        // then
        assertThat(info.surveySimpleResponse().title()).isEqualTo(surveyDto.getTitle());
        assertThat(info.choiceQuestionResponses().size()).isEqualTo(surveyDto.getChoiceQuestions().size());
        assertThat(info.choiceQuestionResponses().get(0).title()).isEqualTo(surveyDto.getChoiceQuestions().get(0).getTitle());
        assertThat(info.subjectiveQuestionResponses().size()).isEqualTo(surveyDto.getSubjectiveQuestions().size());
        assertThat(info.subjectiveQuestionResponses().get(0).title()).isEqualTo(surveyDto.getSubjectiveQuestions().get(0).getTitle());
    }
}