package vestie.survey.domain.survey.controller.response.surveyQuery;

import vestie.survey.domain.surveyquestion.choice.ChoiceQuestion;

import java.util.List;

public record ChoiceQuestionResponse(

        Long id,

        String title,

        String type,

        Long questionOrder,

        /* 다중 선택 가능 여부 */
        boolean isMultiSelectable,

        /* 선택형 질문 옵션들 */
        List<ChoiceOptionResponse> choiceOptionResponses
) {

    public ChoiceQuestionResponse(ChoiceQuestion choiceQuestion) {
        this(
                choiceQuestion.getId(),
                choiceQuestion.getTitle(),
                "choice",
                choiceQuestion.getQuestionOrder(),
                choiceQuestion.isMultiSelectable(),
                choiceQuestion.getChoiceOptions().stream().map(ChoiceOptionResponse::new).toList()
        );
    }
}
