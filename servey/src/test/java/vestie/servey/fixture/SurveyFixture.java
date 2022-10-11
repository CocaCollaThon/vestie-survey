package vestie.servey.fixture;

import static java.time.LocalDate.*;
import static vestie.servey.domain.survey.entity.enums.GenderConstraint.*;

import java.time.LocalDate;

import vestie.servey.domain.survey.entity.value.Constraint;
import vestie.servey.domain.survey.entity.value.ExpectedTime;
import vestie.servey.domain.survey.entity.Survey;
import vestie.servey.domain.survey.entity.enums.GenderConstraint;

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
}