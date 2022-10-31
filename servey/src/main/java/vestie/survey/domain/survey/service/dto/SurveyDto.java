package vestie.survey.domain.survey.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import vestie.survey.domain.survey.entity.Survey;
import vestie.survey.domain.survey.entity.value.Constraint;
import vestie.survey.domain.survey.entity.value.ExpectedTime;
import vestie.survey.domain.surveyquestion.SurveyQuestion;
import vestie.survey.domain.surveyquestion.choice.ChoiceQuestion;
import vestie.survey.domain.surveyquestion.choice.dto.ChoiceQuestionDto;
import vestie.survey.domain.surveyquestion.subjective.SubjectiveQuestion;
import vestie.survey.domain.surveyquestion.subjective.dto.SubjectiveQuestionDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SurveyDto {

    private Long memberId; // 등록자 id

    private String title; // 설문 제목

    private LocalDate startDate; // 설문 시작일

    private LocalDate endDate; // 설문 종료일

    private ExpectedTime expectedTime; // 예상 소요시간

    private Constraint constraint; // 성별, 나이 제약조건

    List<ChoiceQuestionDto> choiceQuestions; // 객관식 질문 목록

    List<SubjectiveQuestionDto> subjectiveQuestions; // 주관식 질문 목록

    public Survey toEntity(){

        // 등록된 설문 엔티티 설정
        Survey survey = Survey.builder()
                .memberId(memberId)
                .title(title)
                .startDate(startDate)
                .endDate(endDate)
                .expectedTime(expectedTime)
                .constraint(constraint)
                .build();

        // 선택형 질문 엔티티 매핑
        List<SurveyQuestion> surveyQuestions =
                new ArrayList<>(
                        choiceQuestions.stream()
                                .map(ChoiceQuestionDto::toEntity).toList()
                );

        // 주관식 질문 엔티티 매핑
        surveyQuestions.addAll(
                subjectiveQuestions.stream().map(SubjectiveQuestionDto::toEntity).toList());

        // 등록된 설문에 질문 세팅
        survey.addAllSurveyField(surveyQuestions);

        return survey;
    }
}
