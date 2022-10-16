package vestie.survey.domain.surveyquestion.subjective.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import vestie.survey.domain.surveyquestion.subjective.SubjectiveQuestion;
import vestie.survey.domain.surveyquestion.subjective.enums.SubjectiveType;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubjectiveQuestionDto {

    private String title;

    private SubjectiveType subjectiveType;

    public SubjectiveQuestion toEntity() {
        return SubjectiveQuestion.builder()
                .title(title)
                .subjectiveType(subjectiveType)
                .build();
    }
}
