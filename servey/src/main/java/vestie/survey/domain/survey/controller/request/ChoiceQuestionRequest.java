package vestie.survey.domain.survey.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import vestie.survey.domain.surveyquestion.choice.ChoiceQuestion;
import vestie.survey.domain.surveyquestion.choice.dto.ChoiceQuestionDto;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChoiceQuestionRequest {

    private String title;

    private boolean isMultiSelectable = false;

    private List<ChoiceOptionRequest> choiceOptions;

    public ChoiceQuestionDto toDto(){
        return ChoiceQuestionDto.builder()
                .title(title)
                .isMultiSelectable(isMultiSelectable)
                .choiceOptions(choiceOptions.stream().map(ChoiceOptionRequest::toDto).toList())
                .build();
    }

}
