package vestie.survey.domain.survey.controller.response;

import vestie.survey.domain.surveyquestion.choice.ChoiceOption;

public record ChoiceOptionResponse(

        Long optionId,

        String name
) {

    public ChoiceOptionResponse(ChoiceOption choiceOption) {
        this(
                choiceOption.getId(),
                choiceOption.getName()
        );
    }
}
