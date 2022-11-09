package vestie.survey.domain.survey.controller.response.surveyResult;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SurveyQuestionResultResponse {
    private Long id; // question id
    private Long questionOrder;

}
