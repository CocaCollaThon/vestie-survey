package vestie.survey.domain.survey.controller.response.surveyResult;

import lombok.Getter;
import lombok.NoArgsConstructor;
import vestie.survey.domain.surveyquestion.exception.NotFoundSurveyQuestionException;

import java.util.Comparator;
import java.util.List;

@Getter
@NoArgsConstructor
public class SurveyResultResponse {

    private int answerCount; // 응답자 수
    private List<SurveyQuestionResultResponse> surveyQuestionResultResponses;

    public SurveyResultResponse(int answerCount, List<SurveyQuestionResultResponse> surveyQuestionResultResponses) {
        this.answerCount = answerCount;
        this.surveyQuestionResultResponses = surveyQuestionResultResponses;
    }

    /**
     * 질문 id로 선택형 질문 결과 객체 반환
     */
    public ChoiceQuestionResultResponse findByChoiceQuestionId(Long questionId) {
        for(SurveyQuestionResultResponse question : surveyQuestionResultResponses) {
            if(question instanceof ChoiceQuestionResultResponse cq && cq.getId() == questionId) {
                return cq;
            }
        }
        throw new NotFoundSurveyQuestionException();
    }

    /**
     * 질문 id로 주과식 질문 결과 반환
     */
    public SubjectiveQuestionResultResponse findBySubjectiveQuestionId(Long questionId) {
        for(SurveyQuestionResultResponse question : surveyQuestionResultResponses) {
            if(question instanceof SubjectiveQuestionResultResponse sq && sq.getId() == questionId) {
                return sq;
            }
        }
        throw new NotFoundSurveyQuestionException();
    }
}
