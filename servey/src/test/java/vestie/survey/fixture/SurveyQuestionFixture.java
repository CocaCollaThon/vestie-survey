package vestie.survey.fixture;

import vestie.survey.domain.survey.controller.request.ChoiceOptionRequest;
import vestie.survey.domain.survey.controller.request.ChoiceQuestionRequest;
import vestie.survey.domain.survey.controller.request.SubjectiveQuestionRequest;
import vestie.survey.domain.surveyquestion.SurveyQuestion;
import vestie.survey.domain.surveyquestion.choice.dto.ChoiceOptionDto;
import vestie.survey.domain.surveyquestion.choice.dto.ChoiceQuestionDto;
import vestie.survey.domain.surveyquestion.subjective.SubjectiveQuestion;
import vestie.survey.domain.surveyquestion.subjective.dto.SubjectiveQuestionDto;
import vestie.survey.domain.surveyquestion.subjective.enums.SubjectiveType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static vestie.survey.domain.surveyquestion.subjective.enums.SubjectiveType.ESSAY;

/**
 * Created by ShinD on 2022/10/11.
 */
public class SurveyQuestionFixture {

	private final static String TITLE = "질문1";

	private final static boolean IS_MULTI_SELECTABLE = true;

	private final static SubjectiveType SUBJECTIVE_TYPE = ESSAY;


	public static SurveyQuestion surveyField(String title) {
		return new SubjectiveQuestion(title, ESSAY);
	}

	public static ChoiceQuestionDto choiceQuestionDto(Long ... ids) {
		List<ChoiceOptionDto> choiceOptionDtos = new ArrayList<>();
		List.of(ids).forEach(i ->
			choiceOptionDtos.add(new ChoiceOptionDto(i.toString()))
		);
		return ChoiceQuestionDto.builder()
				.isMultiSelectable(IS_MULTI_SELECTABLE)
				.title(TITLE)
				.choiceOptions(choiceOptionDtos)
				.build();
	}

	public static SubjectiveQuestionDto subjectiveQuestionDto() {
		return SubjectiveQuestionDto.builder()
				.title(TITLE)
				.subjectiveType(SUBJECTIVE_TYPE)
				.build();
	}

	public static ChoiceQuestionRequest choiceQuestionRequest(Long ... ids) {

		List<ChoiceOptionRequest> choiceOptionRequests =
				new ArrayList<>(Stream.of(ids).map((i) -> new ChoiceOptionRequest(i.toString())).toList());
		return new ChoiceQuestionRequest(TITLE, IS_MULTI_SELECTABLE,choiceOptionRequests);
	}

	public static SubjectiveQuestionRequest subjectiveQuestionRequest() {
		return new SubjectiveQuestionRequest(TITLE, SUBJECTIVE_TYPE.toString());
	}
}
