package vestie.survey.domain.survey.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.support.SimpleTransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import vestie.survey.domain.survey.controller.response.surveyQuery.SurveySimpleResponse;
import vestie.survey.domain.survey.entity.Survey;
import vestie.survey.domain.survey.service.dto.SurveyDto;
import vestie.survey.domain.writtensurvey.entity.WrittenSurvey;
import vestie.survey.domain.writtensurvey.repository.WrittenSurveyRepository;
import vestie.survey.domain.writtensurvey.service.WrittenSurveyService;
import vestie.survey.domain.writtensurvey.service.dto.WrittenSurveyDto;
import vestie.survey.fixture.SurveyFixture;
import vestie.survey.fixture.WrittenSurveyFixture;

import javax.persistence.EntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static vestie.survey.fixture.SurveyFixture.survey;
import static vestie.survey.fixture.SurveyFixture.surveyDto;

class SurveyResultServiceTest {

//    @Mock
//    private WrittenSurveyRepository writtenSurveyRepository = mock(WrittenSurveyRepository.class);
//
//    @Mock
//    private TransactionTemplate transactionTemplate = mock(TransactionTemplate.class);
//
//    // Transaction Template 테스트 실행을 위해 사용
//    Answer<?> mockTransactionAnswer =
//            (i -> i.<TransactionCallback<Long>>getArgument(0).doInTransaction(new SimpleTransactionStatus()));
//
//    private WrittenSurveyService writtenSurveyService =
//            new WrittenSurveyService(writtenSurveyRepository, transactionTemplate);
//
//
//    @Test
//    @DisplayName("등록된 설문의 작성된 설문지 결과를 반환한다.")
//    public void successGetSurveyResultById() throws Exception {
//        // given
//        Long surveyId = 12L;
//        SurveyDto surveyDto = surveyDto();
//        Survey survey = SurveyFixture.surveyWithId(surveyId);
//        WrittenSurveyDto writtenSurveyDto = WrittenSurveyFixture.writtenSurveyDto();
//        WrittenSurvey writtenSurvey = WrittenSurveyFixture.writtenSurveyWithId(surveyId);
//
//
//
//        // when
//        surveyService.save(surveyDto);
//        writtenSurveyService.save(writtenSurveyDto);
//        surveyResultService.getResultById(surveyId);
//
//        // then
//    }
}