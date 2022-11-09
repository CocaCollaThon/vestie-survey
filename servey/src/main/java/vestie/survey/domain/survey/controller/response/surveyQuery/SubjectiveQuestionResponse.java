package vestie.survey.domain.survey.controller.response.surveyQuery;

import vestie.survey.domain.surveyquestion.subjective.SubjectiveQuestion;
import vestie.survey.domain.surveyquestion.subjective.enums.SubjectiveType;

public record SubjectiveQuestionResponse(

        Long id,

        String title,

        String type,

        Long questionOrder,

        /* SHORT_ANSWER, ESSAY */
        SubjectiveType subjectiveType
) {

    public SubjectiveQuestionResponse(SubjectiveQuestion sq) {
        this(
          sq.getId(),
          sq.getTitle(),
          "subjective",
          sq.getQuestionOrder(),
          sq.getSubjectiveType()
        );
    }
}
