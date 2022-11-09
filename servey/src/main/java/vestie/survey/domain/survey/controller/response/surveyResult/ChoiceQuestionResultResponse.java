package vestie.survey.domain.survey.controller.response.surveyResult;

import lombok.Getter;
import vestie.survey.domain.surveyquestion.exception.NotFoundChoiceQuestionOptionException;

import java.util.List;

@Getter
public class ChoiceQuestionResultResponse extends SurveyQuestionResultResponse {

    // 질문 옵션 별 응답 개수
    private List<SelectedChoiceOptionCountResponse> selectedChoiceOptionCountResponses;

    public ChoiceQuestionResultResponse(Long id, Long questionOrder, List<SelectedChoiceOptionCountResponse> selectedChoiceOptionCountResponses) {
        super(id, questionOrder);
        this.selectedChoiceOptionCountResponses = selectedChoiceOptionCountResponses;
    }

    /**
     * 질문 옵션 id로 선택된 질문 옵션 객체 반환
     */
    public SelectedChoiceOptionCountResponse findOptionById(Long id) {
        for( SelectedChoiceOptionCountResponse selectedChoiceOptionCountResponse : selectedChoiceOptionCountResponses) {
            if( selectedChoiceOptionCountResponse.getChoiceOptionId() == id) {
                return selectedChoiceOptionCountResponse;
            }
        }
        throw new NotFoundChoiceQuestionOptionException();
    }
}
