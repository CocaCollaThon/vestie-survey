package vestie.survey.domain.surveyquestion.choice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import vestie.survey.domain.surveyquestion.choice.ChoiceQuestion;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class ChoiceQuestionDto {

    private String title;

    @Value("false")
    private boolean isMultiSelectable;

    private List<ChoiceOptionDto> choiceOptions;

    public ChoiceQuestion toEntity(){
        // 선택형 질문 생성
        var choiceQuestion = new ChoiceQuestion(title, isMultiSelectable);

        // 질문에 옵션 세팅
        choiceQuestion.addAllChoiceOption(choiceOptions.stream().map(ChoiceOptionDto::toEntity).toList());

        return choiceQuestion;
    }

}
