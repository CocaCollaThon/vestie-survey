package vestie.survey.fixture;

import static vestie.survey.domain.surveyquestion.subjective.enums.SubjectiveType.*;

import vestie.survey.domain.surveyquestion.SurveyQuestion;
import vestie.survey.domain.surveyquestion.subjective.SubjectiveQuestion;

/**
 * Created by ShinD on 2022/10/11.
 */
public class SurveyQuestionFixture {
	private final static String TITLE = "질문1";

	public static SurveyQuestion surveyField(String title) {
		return new SubjectiveQuestion(title, ESSAY);
	}
}
