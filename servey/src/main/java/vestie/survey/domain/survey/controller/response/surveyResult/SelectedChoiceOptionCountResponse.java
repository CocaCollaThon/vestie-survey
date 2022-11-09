package vestie.survey.domain.survey.controller.response.surveyResult;

import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class SelectedChoiceOptionCountResponse {
    private Long choiceOptionId;
    private int count;

    public SelectedChoiceOptionCountResponse(Long choiceOptionId){
        this.choiceOptionId = choiceOptionId;
        this.count = 0;
    }

    public void countUp(){
        this.count += 1;
    }
}
