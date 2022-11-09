package vestie.survey.domain.survey.controller.response.surveyResult;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class SubjectiveQuestionResultResponse extends SurveyQuestionResultResponse {

    private List<String> answers;

    public SubjectiveQuestionResultResponse(Long id, Long questionOrder){
        super(id, questionOrder);
        this.answers = new ArrayList<>();
    }
}
