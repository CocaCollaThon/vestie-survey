package vestie.survey.domain.survey.controller.response;

import vestie.survey.domain.survey.entity.Survey;

import java.time.LocalDate;

public record ClosedSurveySimpleResponse(
        /* 설문 ID */
        Long id,

        /* 설문 작성자 ID */
        Long memberId,

        String title,

        LocalDate startDate,

        LocalDate endDate,

        int participants
) {
    public ClosedSurveySimpleResponse(Survey survey, int participants){
        this(
                survey.getId(),
                survey.getMemberId(),
                survey.getTitle(),
                survey.getStartDate(),
                survey.getEndDate(),
                participants
        );
    }
}
