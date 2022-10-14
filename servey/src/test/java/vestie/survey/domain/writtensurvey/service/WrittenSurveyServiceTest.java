package vestie.survey.domain.writtensurvey.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static vestie.survey.fixture.WrittenSurveyFixture.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.transaction.support.SimpleTransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import vestie.survey.domain.writtensurvey.entity.WrittenSurvey;
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

	private WrittenSurveyService writtenSurveyService =
		new WrittenSurveyService(writtenSurveyRepository, transactionTemplate);

	@Test
	@DisplayName("설문 답변 작성에 성공하는 일반적인 케이스")
	public void successSaveTest() throws Exception {

		//given
		Long returnId = 10L;
		WrittenSurveyDto dto = writtenSurveyDto();
		WrittenSurvey writtenSurvey = writtenSurveyWithId(returnId);

		when(writtenSurveyRepository.save(any())).thenReturn(writtenSurvey);
		when(transactionTemplate.execute(any()))
			.thenAnswer(invocation -> invocation.<TransactionCallback<Long>>getArgument(0).doInTransaction(new SimpleTransactionStatus()));

		//when
		Long aLong = writtenSurveyService.save(dto);

		//then
		Assertions.assertThat(aLong).isEqualTo(returnId);
		verify(transactionTemplate, times(1)).execute(any());
		verify(writtenSurveyRepository, times(1)).save(any(WrittenSurvey.class));
	}

	@Test
	@DisplayName("설문 답변 저장 시 트랜잭션이 실행되는지 확인")
	public void testTransactionIsExecuted() throws Exception {

		//given
		WrittenSurveyDto dto = writtenSurveyDto();

		//when
		Long aLong = writtenSurveyService.save(dto);

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