package vestie.survey.domain.survey.controller.response;

import vestie.survey.domain.survey.entity.Survey;
import vestie.survey.domain.survey.entity.value.Constraint;

import java.time.LocalDate;

public record SurveySimpleResponse(

        /* 설문 ID */
        Long id,

        /* 설문 작성자 ID */
        Long memberId,

        String title,

        LocalDate startDate,

        LocalDate endDate,

        int expectedTime,

        /* 문항 수 */
        int questionNumber,

        /* 성별, 나이 제약조건 */
        Constraint constraint,

        int point
) {

    public SurveySimpleResponse(Survey survey) {
        this(
                survey.getId(),
                survey.getMemberId(),
                survey.getTitle(),
                survey.getStartDate(),
                survey.getEndDate(),
                survey.getExpectedTime().getMinute(),
                survey.getSurveyQuestions().size(),
                survey.getConstraint(),
                10  // TODO 포인트 구현 이후 변경
        );
    }
}
