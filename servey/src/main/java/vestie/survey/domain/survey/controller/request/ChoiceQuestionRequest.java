package vestie.survey.domain.survey.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import vestie.survey.domain.surveyquestion.choice.ChoiceQuestion;
import vestie.survey.domain.surveyquestion.choice.dto.ChoiceQuestionDto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChoiceQuestionRequest {

    @NotBlank
    private String title;

    @NotNull
    private Long questionOrder;

    private boolean isMultiSelectable = false;

    @Valid
    private List<ChoiceOptionRequest> choiceOptions;

    public ChoiceQuestionDto toDto(){
        return ChoiceQuestionDto.builder()
                .title(title)
                .questionOrder(questionOrder)
                .isMultiSelectable(isMultiSelectable)
                .choiceOptions(choiceOptions.stream().map(ChoiceOptionRequest::toDto).toList())
                .build();
    }

}
