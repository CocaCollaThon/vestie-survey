package vestie.survey.domain.surveyquestion.choice.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import vestie.survey.domain.surveyquestion.choice.ChoiceOption;

@Getter
@AllArgsConstructor
public class ChoiceOptionDto {

    private String name; // 옵션 이름

    public ChoiceOption toEntity(){
        return new ChoiceOption(name);
    }

}
