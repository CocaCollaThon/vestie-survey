package vestie.survey.domain.writtensurvey.service;

import static java.util.Optional.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static vestie.survey.fixture.WrittenSurveyFixture.*;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.stubbing.Answer;
import org.springframework.transaction.support.SimpleTransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import vestie.survey.domain.writtensurvey.entity.WrittenSurvey;
import vestie.survey.domain.writtensurvey.exception.DuplicateSurveyParticipationException;
import vestie.survey.domain.writtensurvey.repository.WrittenSurveyRepository;
import vestie.survey.domain.writtensurvey.service.dto.WrittenSurveyDto;

/**
 * Created by ShinD on 2022/10/13.
 */
class WrittenSurveyServiceTest {

	@Mock
	private WrittenSurveyRepository writtenSurveyRepository = mock(WrittenSurveyRepository.class);
	@Mock
	private TransactionTemplate transactionTemplate = mock(TransactionTemplate.class);

	// Transaction Template 테스트 실행을 위해 사용
	Answer<?> mockTransactionAnswer =
		(i -> i.<TransactionCallback<Long>>getArgument(0).doInTransaction(new SimpleTransactionStatus()));

	private WrittenSurveyService writtenSurveyService =
		new WrittenSurveyService(writtenSurveyRepository, transactionTemplate);

	@Test
	@DisplayName("설문 답변 작성에 성공하는 일반적인 케이스")
	public void successSaveTest() throws Exception {

		//given
		Long returnId = 10L;
		WrittenSurveyDto dto = writtenSurveyDto();
		WrittenSurvey writtenSurvey = writtenSurveyWithId(returnId);
		Long surveyId = writtenSurvey.getSurveyId();
		Long memberId = writtenSurvey.getMemberId();

		when(writtenSurveyRepository.findBySurveyIdAndMemberId(surveyId, memberId))
			.thenReturn(empty());
		when(writtenSurveyRepository.save(any()))
			.thenReturn(writtenSurvey);
		when(transactionTemplate.execute(any()))
			.thenAnswer(mockTransactionAnswer);


		//when
		Long aLong = writtenSurveyService.save(dto);

		//then
		Assertions.assertThat(aLong).isEqualTo(returnId);
		verify(transactionTemplate, times(1)).execute(any());
		verify(writtenSurveyRepository, times(1)).save(any(WrittenSurvey.class));
	}

	@Test
	@DisplayName("설문에 대해 중복 응답한 경우 예외 발생")
	public void saveFailedWhenDuplicatedParticipant() throws Exception {

		//given
		Long returnId = 10L;
		WrittenSurveyDto dto = writtenSurveyDto();
		WrittenSurvey writtenSurvey = writtenSurveyWithId(returnId);
		Long surveyId = writtenSurvey.getSurveyId();
		Long memberId = writtenSurvey.getMemberId();

		when(writtenSurveyRepository.findBySurveyIdAndMemberId(surveyId, memberId))
			.thenReturn(Optional.of(writtenSurvey));
		when(writtenSurveyRepository.save(any()))
			.thenReturn(writtenSurvey);
		when(transactionTemplate.execute(any()))
			.thenAnswer(mockTransactionAnswer);

		//when, then
		assertThrows(DuplicateSurveyParticipationException.class, () ->writtenSurveyService.save(dto));
	}

	@Test
	@DisplayName("설문 답변 저장 시 트랜잭션이 실행되는지 확인")
	public void testTransactionIsExecuted() throws Exception {

		//given
		WrittenSurveyDto dto = writtenSurveyDto();

		//when
		writtenSurveyService.save(dto);

		//then
		verify(transactionTemplate, times(1)).execute(any());
	}

	@Test
	@DisplayName("엔티티 필드 검증 메서드 실행되는지 확인")
	public void checkFieldIsNotNullWhenSaveCalled() throws Exception {

		//given
		WrittenSurveyDto dto =
			new WrittenSurveyDto(null, 0, null, null, null, null);

		//when, then
		assertThrows(RuntimeException.class, () -> writtenSurveyService.save(dto));
	}
}