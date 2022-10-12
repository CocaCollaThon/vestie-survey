package vestie.servey.fixture;

import static vestie.servey.domain.surveyfield.subjective.enums.SubjectiveType.*;

import vestie.servey.domain.surveyfield.SurveyField;
import vestie.servey.domain.surveyfield.subjective.SubjectiveQuestion;

/**
 * Created by ShinD on 2022/10/11.
 */
public class SurveyFieldFixture {
	private final static String TITLE = "질문1";

	public static SurveyField surveyField(String title) {
		return new SubjectiveQuestion(title, ESSAY);
	}
}
