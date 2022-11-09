package vestie.survey.domain.survey.controller.response.surveyQuery;

import vestie.survey.domain.survey.entity.Survey;

import java.util.List;

public record SurveyCompleteInfoResponse(

        /* 설문 관련 간단한 정보 */
        SurveySimpleResponse surveySimpleResponse,

        /* 객관식 질문 정보들 */
        List<ChoiceQuestionResponse> choiceQuestionResponses,

        /* 주관식(단답형, 서술형) 질문 정보들 */
        List<SubjectiveQuestionResponse> subjectiveQuestionResponses
) {

    public SurveyCompleteInfoResponse(Survey survey,
                                      List<ChoiceQuestionResponse> choiceQuestionResponses,
                                      List<SubjectiveQuestionResponse> subjectiveQuestionResponses) {
        this(new SurveySimpleResponse(survey),
                choiceQuestionResponses,
                subjectiveQuestionResponses
        );
    }
}
