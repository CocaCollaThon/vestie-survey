package vestie.survey.fixture;

import static org.springframework.test.util.ReflectionTestUtils.*;

import java.util.List;

import vestie.survey.domain.answer.choice.ChoiceQuestionAnswer;
import vestie.survey.domain.answer.subjective.SubjectiveQuestionAnswer;
import vestie.survey.domain.writtensurvey.controller.request.ChoiceQuestionAnswerRequest;
import vestie.survey.domain.writtensurvey.controller.request.SubjectiveQuestionAnswerRequest;
import vestie.survey.domain.writtensurvey.service.dto.ChoiceQuestionAnswerDto;
import vestie.survey.domain.writtensurvey.service.dto.SubjectiveQuestionAnswerDto;

/**
 * Created by ShinD on 2022/10/13.
 */
public class AnswerFixture {

	private static final Long ID = 1L; // 대상 질문 양식 Id
	private static final Long SURVEY_QUESTION_ID = 1L; // 대상 질문 양식 Id

	private static final String ANSWER = "Mock 답변"; // 답변 내용

	public static ChoiceQuestionAnswer noIdChoiceQuestionAnswer() {
		return new ChoiceQuestionAnswer(SURVEY_QUESTION_ID);
	}

	public static ChoiceQuestionAnswer choiceQuestionAnswerWithId() {
		ChoiceQuestionAnswer choiceQuestionAnswer = new ChoiceQuestionAnswer(SURVEY_QUESTION_ID);
		setField(choiceQuestionAnswer, "id", ID);
		return choiceQuestionAnswer;
	}

	public static SubjectiveQuestionAnswer noIdSubjectiveQuestionAnswer() {
		return new SubjectiveQuestionAnswer(SURVEY_QUESTION_ID, ANSWER);
	}

	public static SubjectiveQuestionAnswer subjectiveQuestionAnswerWithId() {
		SubjectiveQuestionAnswer choiceQuestionAnswer = new SubjectiveQuestionAnswer(SURVEY_QUESTION_ID, ANSWER);
		setField(choiceQuestionAnswer, "id", ID);
		return choiceQuestionAnswer;
	}

	public static ChoiceQuestionAnswerDto choiceQuestionAnswerDto(Long ... ids) {
		return new ChoiceQuestionAnswerDto(SURVEY_QUESTION_ID, List.of(ids));
	}

	public static SubjectiveQuestionAnswerDto subjectiveQuestionAnswerDto() {
		return new SubjectiveQuestionAnswerDto(SURVEY_QUESTION_ID, ANSWER);
	}

	public static ChoiceQuestionAnswerRequest choiceQuestionAnswerRequest(Long ... ids) {
		return new ChoiceQuestionAnswerRequest(SURVEY_QUESTION_ID, List.of(ids));
	}

	public static SubjectiveQuestionAnswerRequest subjectiveQuestionAnswerRequest() {
		return new SubjectiveQuestionAnswerRequest(SURVEY_QUESTION_ID, ANSWER);
	}
}
