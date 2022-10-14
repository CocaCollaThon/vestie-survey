package vestie.servey.domain.writtensurvey.repository;

import static org.assertj.core.api.Assertions.*;
import static vestie.servey.fixture.AnswerFixture.*;
import static vestie.servey.fixture.WrittenSurveyFixture.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import vestie.servey.domain.answer.Answer;
import vestie.servey.domain.answer.choice.ChoiceQuestionAnswer;
import vestie.servey.domain.answer.choice.SelectedChoiceOption;
import vestie.servey.domain.writtensurvey.entity.WrittenSurvey;

/**
 * Created by ShinD on 2022/10/14.
 */
@DataJpaTest
class WrittenSurveyRepositoryTest {

	@Autowired
	private WrittenSurveyRepository writtenSurveyRepository;

	@Test
	@DisplayName("설문 답변 작성 시, 답변 필드들까지 한번에 저장 + 객관식 답변의 경우 선택한 옵션들도 모두 저장")
	public void successSavedWrittenSurveyAndAnswers() throws Exception {
		//given

		// 설문 생성
		WrittenSurvey writtenSurvey = noIdWrittenSurvey();

		// 답변 생성
		ArrayList<Answer> answers = new ArrayList<>();
		int subjectAnswerCount = 10;
		for (int i = 0; i < subjectAnswerCount; i++) {
			answers.add(noIdSubjectiveQuestionAnswer());
		}
		int choiceAnswerCount = 10;
		for (int i = 0; i < choiceAnswerCount; i++) {
			ChoiceQuestionAnswer choiceQuestionAnswer = noIdChoiceQuestionAnswer();

			choiceQuestionAnswer.confirmSelectedChoiceOptions(List.of(1L, 2L, 3L));

			answers.add(choiceQuestionAnswer);
		}

		// 답변 세팅
		writtenSurvey.confirmWrittenSurveyQuestions(answers);

		//when
		Long saveId = writtenSurveyRepository.save(writtenSurvey).getId();

		//then
		WrittenSurvey findWrittenSurvey = writtenSurveyRepository.findById(saveId).get();

		assertThat(findWrittenSurvey.getAnswers().size()).isEqualTo(subjectAnswerCount + choiceAnswerCount);
		findWrittenSurvey.checkFieldNotNull();

		for (Answer answer : findWrittenSurvey.getAnswers()) {
			assertThat(answer.getWrittenSurvey()).isEqualTo(findWrittenSurvey);
			if (answer instanceof ChoiceQuestionAnswer ca) {
				assertThat(ca.getSelectedChoiceOptions().size()).isEqualTo(3);
				for (SelectedChoiceOption selectedChoiceOption : ca.getSelectedChoiceOptions()) {
					assertThat(selectedChoiceOption.getChoiceQuestionAnswer()).isEqualTo(ca);
				}
			}
		}
	}
}