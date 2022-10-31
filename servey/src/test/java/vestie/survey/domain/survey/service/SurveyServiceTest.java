package vestie.survey.domain.survey.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.stubbing.Answer;
import org.springframework.transaction.support.SimpleTransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import vestie.survey.domain.survey.entity.Survey;
import vestie.survey.domain.survey.repository.SurveyRepository;
import vestie.survey.domain.survey.service.dto.SurveyDto;
import vestie.survey.fixture.SurveyFixture;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static vestie.survey.fixture.SurveyFixture.surveyDto;

class SurveyServiceTest {

    @Mock
    private final SurveyRepository surveyRepository = mock(SurveyRepository.class);

    @Mock
    private final TransactionTemplate transactionTemplate = mock(TransactionTemplate.class);

    private final SurveyService surveyService = new SurveyService(surveyRepository, transactionTemplate);

    Answer<?> mockTransactionAnswer =
            (i -> i.<TransactionCallback<Long>>getArgument(0).doInTransaction(new SimpleTransactionStatus()));

    @Test
    @DisplayName("설문 등록 성공 테스트")
    void registerTest() {
        // given
        Long returnId = 10L;
        SurveyDto surveyDto = surveyDto();
        Survey survey = SurveyFixture.surveyWithId(returnId);

        when(transactionTemplate.execute(any())).thenAnswer(mockTransactionAnswer);
        when(surveyRepository.save(any())).thenReturn(survey);

        // when
        Long savedId = surveyService.save(surveyDto);

        // then
        Assertions.assertThat(savedId).isEqualTo(returnId);
    }

    @Test
    @DisplayName("설문 등록 저장 시 트랜잭션이 실행되는지 확인")
    public void testTransactionIsExecuted() throws Exception {
        //given
        SurveyDto dto = surveyDto();

        //when
        Long aLong = surveyService.save(dto);

        //then
        verify(transactionTemplate, times(1)).execute(any());
    }

    @Test
    @DisplayName("엔티티 필드 검증 메서드 실행되는지 확인")
    public void checkFieldIsNotNullWhenSaveCalled() throws Exception {
        //given
        SurveyDto dto =
                new SurveyDto(null, null, null,
                        null, null, null,
                        null, null);

        //when, then
        assertThrows(RuntimeException.class, () -> surveyService.save(dto));
    }

}