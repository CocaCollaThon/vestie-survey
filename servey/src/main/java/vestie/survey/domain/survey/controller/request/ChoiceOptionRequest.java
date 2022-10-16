package vestie.survey.domain.survey.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import vestie.survey.domain.surveyquestion.choice.dto.ChoiceOptionDto;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChoiceOptionRequest {

    private String name; // 옵션 이름

    public ChoiceOptionDto toDto(){
        return new ChoiceOptionDto(name);
    }

}
