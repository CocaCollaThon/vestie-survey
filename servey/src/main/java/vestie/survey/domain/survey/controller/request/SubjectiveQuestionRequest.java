package vestie.survey.domain.survey.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import vestie.survey.domain.surveyquestion.subjective.dto.SubjectiveQuestionDto;
import vestie.survey.domain.surveyquestion.subjective.enums.SubjectiveType;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SubjectiveQuestionRequest{

    private String title; // 질문 내용

    private String subjectiveType; // 주관식 타입

    public SubjectiveQuestionDto toDto(){
        return SubjectiveQuestionDto.builder()
                .title(title)
                .subjectiveType(SubjectiveType.valueOf(subjectiveType))
                .build();
    }

}
