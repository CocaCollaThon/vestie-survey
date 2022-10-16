package vestie.survey.fixture;

import static java.time.LocalDate.*;
import static org.springframework.test.util.ReflectionTestUtils.setField;
import static vestie.survey.domain.survey.entity.enums.GenderConstraint.*;
import static vestie.survey.fixture.AnswerFixture.choiceQuestionAnswerDto;
import static vestie.survey.fixture.AnswerFixture.subjectiveQuestionAnswerDto;
import static vestie.survey.fixture.SurveyQuestionFixture.choiceQuestionDto;
import static vestie.survey.fixture.SurveyQuestionFixture.subjectiveQuestionDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import vestie.survey.domain.survey.entity.Survey;
import vestie.survey.domain.survey.entity.enums.GenderConstraint;
import vestie.survey.domain.survey.entity.value.Constraint;
import vestie.survey.domain.survey.entity.value.ExpectedTime;
import vestie.survey.domain.survey.service.dto.SurveyDto;
import vestie.survey.domain.surveyquestion.choice.dto.ChoiceQuestionDto;
import vestie.survey.domain.surveyquestion.subjective.dto.SubjectiveQuestionDto;
import vestie.survey.domain.writtensurvey.entity.WrittenSurvey;
import vestie.survey.domain.writtensurvey.service.dto.ChoiceQuestionAnswerDto;
import vestie.survey.domain.writtensurvey.service.dto.SubjectiveQuestionAnswerDto;

/**
 * Created by ShinD on 2022/10/11.
 */
public class SurveyFixture {
	private final static Long ID = 1L; // PK
	private final static Long MEMBER_ID = 1L; // 등록자 회원 Id(PK)
	private final static String TITLE = "sample title"; // 설문 제목
	private final static LocalDate START_DATE = of(2000, 10, 4); // 설문 시작일
	private final static LocalDate END_DATE = of(2000, 11, 4); // 설문 종료일
	private final static ExpectedTime EXPECTED_TIME = new ExpectedTime(50); // 예상 소요시간 (분)
	private final static GenderConstraint GENDER_CONSTRAINT = NO_CONSTRAINT; // 성별 제약조건 [ONLY_MAN, ONLY_WOMAN, NO_CONSTRAINT]
	private final static int MIN_AGE_CONSTRAINT = 0; // 최소 나이 제약조건
	private final static int MAX_AGE_CONSTRAINT = 500; // 최대 나이 제약조건

	private final static Constraint CONSTRAINT = Constraint.builder()
		.genderConstraint(GENDER_CONSTRAINT)
		.maxAgeConstraint(MAX_AGE_CONSTRAINT)
		.minAgeConstraint(MIN_AGE_CONSTRAINT)
		.build(); // 성별, 나이 제약조건



	public static Survey survey() {
		return Survey.builder()
			.memberId(MEMBER_ID)
			.title(TITLE)
			.startDate(START_DATE)
			.endDate(END_DATE)
			.expectedTime(EXPECTED_TIME)
			.constraint(CONSTRAINT)
			.build();
	}

	public static SurveyDto surveyDto(){
		List<ChoiceQuestionDto> choiceQuestionDtos = new ArrayList<>();
		List<SubjectiveQuestionDto> subjectiveQuestionDtos = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			choiceQuestionDtos.add(choiceQuestionDto(5L * i, 5L * i + 1, 5L * i + 2, 5L * i + 3, 5L * i + 4));
			subjectiveQuestionDtos.add(subjectiveQuestionDto());
		}
		return SurveyDto.builder()
				.memberId(MEMBER_ID)
				.title(TITLE)
				.startDate(START_DATE)
				.endDate(END_DATE)
				.expectedTime(EXPECTED_TIME)
				.constraint(CONSTRAINT)
				.choiceQuestions(choiceQuestionDtos)
				.subjectiveQuestions(subjectiveQuestionDtos)
				.build();
	}

	public static Survey surveyWithId(Long id) {
		List<ChoiceQuestionDto> choiceQuestionDtos = new ArrayList<>();
		List<SubjectiveQuestionDto> subjectiveQuestionDtos = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			choiceQuestionDtos.add(choiceQuestionDto(5L * i, 5L * i + 1, 5L * i + 2, 5L * i + 3, 5L * i + 4));
			subjectiveQuestionDtos.add(subjectiveQuestionDto());
		}

		Survey survey = Survey.builder()
				.memberId(MEMBER_ID)
				.title(TITLE)
				.startDate(START_DATE)
				.endDate(END_DATE)
				.expectedTime(EXPECTED_TIME)
				.constraint(CONSTRAINT)
				.build();

		setField(survey, "id", id);

		return survey;
	}
}