package vestie.survey.domain.survey.controller.response;

import vestie.survey.domain.surveyquestion.subjective.SubjectiveQuestion;
import vestie.survey.domain.surveyquestion.subjective.enums.SubjectiveType;

public record SubjectiveQuestionResponse(

        Long id,

        String title,

        /* SHORT_ANSWER, ESSAY */
        SubjectiveType subjectiveType
) {

    public SubjectiveQuestionResponse(SubjectiveQuestion sq) {
        this(
          sq.getId(),
          sq.getTitle(),
          sq.getSubjectiveType()
        );
    }
}
