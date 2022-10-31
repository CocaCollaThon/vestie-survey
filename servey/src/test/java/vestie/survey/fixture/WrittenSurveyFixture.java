package vestie.survey.fixture;

import static org.springframework.test.util.ReflectionTestUtils.*;
import static vestie.survey.fixture.AnswerFixture.*;

import java.util.ArrayList;
import java.util.List;

import vestie.survey.domain.writtensurvey.controller.request.ChoiceQuestionAnswerRequest;
import vestie.survey.domain.writtensurvey.controller.request.SubjectiveQuestionAnswerRequest;
import vestie.survey.domain.writtensurvey.controller.request.WrittenSurveyRequest;
import vestie.survey.domain.writtensurvey.entity.WrittenSurvey;
import vestie.survey.domain.writtensurvey.entity.enums.Gender;
import vestie.survey.domain.writtensurvey.service.dto.ChoiceQuestionAnswerDto;
import vestie.survey.domain.writtensurvey.service.dto.SubjectiveQuestionAnswerDto;
import vestie.survey.domain.writtensurvey.service.dto.WrittenSurveyDto;

/**
 * Created by ShinD on 2022/10/13.
 */
public class WrittenSurveyFixture {

	private final static Long ID = 1L;

	private final static Long SURVEY_ID = 2L; // 대상 설문 양식 ID

	private final static Long MEMBER_ID = 10L; // 작성자 아이디

	private final static int WRITER_AGE = 20; // 작성자 나이

	private final static Gender WRITER_GENDER = Gender.MAN; // 작성자 성별 [MAN, WOMAN]

	public static WrittenSurvey emptyWrittenSurvey() {
		return WrittenSurvey.builder().build();
	}

	public static WrittenSurvey noIdWrittenSurvey() {
		return WrittenSurvey.builder()
			.surveyId(SURVEY_ID)
			.memberId(MEMBER_ID)
			.writerAge(WRITER_AGE)
			.writerGender(WRITER_GENDER)
			.build();
	}

	public static WrittenSurvey writtenSurveyWithId(Long id) {
		WrittenSurvey writtenSurvey = WrittenSurvey.builder()
			.surveyId(SURVEY_ID)
			.memberId(MEMBER_ID)
			.writerAge(WRITER_AGE)
			.writerGender(WRITER_GENDER)
			.build();

		setField(writtenSurvey, "id", id);
		return writtenSurvey;
	}

	public static WrittenSurveyDto writtenSurveyDto(
		List<ChoiceQuestionAnswerDto> choiceQuestionAnswerDtos,
		List<SubjectiveQuestionAnswerDto> subjectiveQuestionAnswerDtos
	) {
		return new WrittenSurveyDto(
			MEMBER_ID,
			WRITER_AGE,
			WRITER_GENDER,
			SURVEY_ID,
			choiceQuestionAnswerDtos,
			subjectiveQuestionAnswerDtos
		);
	}

	public static WrittenSurveyDto writtenSurveyDto() {
		List<ChoiceQuestionAnswerDto> choiceQuestionAnswerDtos = new ArrayList<>();
		List<SubjectiveQuestionAnswerDto> subjectiveQuestionAnswerDtos = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			choiceQuestionAnswerDtos.add(
				choiceQuestionAnswerDto(5L * i, 5L * i + 1, 5L * i + 2, 5L * i + 3, 5L * i + 4));
			subjectiveQuestionAnswerDtos.add(subjectiveQuestionAnswerDto());
		}
		return new WrittenSurveyDto(
			MEMBER_ID,
			WRITER_AGE,
			WRITER_GENDER,
			SURVEY_ID,
			choiceQuestionAnswerDtos,
			subjectiveQuestionAnswerDtos
		);
	}

	public static WrittenSurveyRequest writtenSurveyRequest(
		List<ChoiceQuestionAnswerRequest> choiceQuestionAnswerRequests,
		List<SubjectiveQuestionAnswerRequest> subjectiveQuestionAnswerRequests
	) {
		return new WrittenSurveyRequest(
			WRITER_AGE,
			WRITER_GENDER,
			SURVEY_ID,
			choiceQuestionAnswerRequests,
			subjectiveQuestionAnswerRequests
		);
	}

	public static WrittenSurveyRequest writtenSurveyRequest() {
		List<ChoiceQuestionAnswerRequest> choiceQuestionAnswerRequests = new ArrayList<>();
		List<SubjectiveQuestionAnswerRequest> subjectiveQuestionAnswerRequests = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			choiceQuestionAnswerRequests.add(
				choiceQuestionAnswerRequest(5L * i, 5L * i + 1, 5L * i + 2, 5L * i + 3, 5L * i + 4));
			subjectiveQuestionAnswerRequests.add(subjectiveQuestionAnswerRequest());
		}
		return new WrittenSurveyRequest(
			WRITER_AGE,
			WRITER_GENDER,
			SURVEY_ID,
			choiceQuestionAnswerRequests,
			subjectiveQuestionAnswerRequests
		);
	}

	public static WrittenSurveyRequest emptyWrittenSurveyRequest() {
		return new WrittenSurveyRequest(
			0,
			null,
			null,
			new ArrayList<>(),
			new ArrayList<>()
		);
	}
}
