package vestie.servey.fixture;

import vestie.servey.domain.surveyfield.entity.SurveyField;

/**
 * Created by ShinD on 2022/10/11.
 */
public class SurveyFieldFixture {
	private final static String TITLE = "질문1";

	public static SurveyField surveyField(String title) {
		SurveyField sf = new SurveyField(title);
		return sf;
	}
}
