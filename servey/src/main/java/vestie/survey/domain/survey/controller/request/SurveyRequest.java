package vestie.survey.domain.survey.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.apache.tomcat.util.bcel.Const;
import vestie.survey.domain.survey.entity.enums.GenderConstraint;
import vestie.survey.domain.survey.entity.value.Constraint;
import vestie.survey.domain.survey.entity.value.ExpectedTime;
import vestie.survey.domain.survey.service.dto.SurveyDto;

import java.time.LocalDate;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SurveyRequest {

    private Long memberId; // 등록자 id

    private String title; // 설문 제목

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate startDate; // 설문 시작일

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate endDate; // 설문 종료일

    private int expectedTime; // 예상 소요시간

    private String genderConstraint; // 성별 제약조건

    private int minAgeConstraint; // 최소 나이 제약조건

    private int maxAgeConstraint; // 최대 나이 제약조건

    List<ChoiceQuestionRequest> choiceQuestions; // 객관식 질문 목록

    List<SubjectiveQuestionRequest> subjectiveQuestions; // 주관식 질문 목록

    public SurveyDto toDto(){

        // 제약 조건 생성
        var constraint = Constraint.builder()
                .genderConstraint(GenderConstraint.valueOf(genderConstraint))
                .minAgeConstraint(minAgeConstraint)
                .maxAgeConstraint(maxAgeConstraint)
                .build();

        return SurveyDto.builder()
                .memberId(memberId)
                .title(title)
                .startDate(startDate)
                .endDate(endDate)
                .expectedTime(new ExpectedTime(expectedTime))
                .constraint(constraint)
                .choiceQuestions(choiceQuestions.stream().map(ChoiceQuestionRequest::toDto).toList())
                .subjectiveQuestions(subjectiveQuestions.stream().map(SubjectiveQuestionRequest::toDto).toList())
                .build();
    }
}
