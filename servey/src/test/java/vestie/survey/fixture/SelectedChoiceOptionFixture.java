package vestie.survey.fixture;

import org.springframework.test.util.ReflectionTestUtils;

import vestie.survey.domain.answer.choice.SelectedChoiceOption;

/**
 * Created by ShinD on 2022/10/14.
 */
public class SelectedChoiceOptionFixture {

	private final static Long ID = 1L; // 선택한 옵션 양식 ID
	private final static Long CHOICE_OPTION_ID = 1L; // 선택한 옵션 양식 ID

	public static SelectedChoiceOption noIdSelectedChoiceOption() {
		return new SelectedChoiceOption(CHOICE_OPTION_ID);
	}

	public static SelectedChoiceOption selectedChoiceOptionWithId(Long id) {
		SelectedChoiceOption option = new SelectedChoiceOption(CHOICE_OPTION_ID);
		ReflectionTestUtils.setField(option, "id", ID);
		return option;
	}
}
