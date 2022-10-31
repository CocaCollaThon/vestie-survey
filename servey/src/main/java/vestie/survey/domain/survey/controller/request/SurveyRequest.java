package vestie.survey.domain.survey.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import vestie.survey.domain.survey.entity.enums.GenderConstraint;
import vestie.survey.domain.survey.entity.value.Constraint;
import vestie.survey.domain.survey.entity.value.ExpectedTime;
import vestie.survey.domain.survey.service.dto.SurveyDto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SurveyRequest {

    @NotBlank
    private String title; // 설문 제목

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate startDate; // 설문 시작일

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate endDate; // 설문 종료일

    @NotNull
    private int expectedTime; // 예상 소요시간

    @NotBlank
    private String genderConstraint; // 성별 제약조건

    @NotNull
    private int minAgeConstraint; // 최소 나이 제약조건

    @NotNull
    private int maxAgeConstraint; // 최대 나이 제약조건

    @Valid
    List<ChoiceQuestionRequest> choiceQuestions; // 객관식 질문 목록

    @Valid
    List<SubjectiveQuestionRequest> subjectiveQuestions; // 주관식 질문 목록

    public SurveyDto toDto(Long memberId){

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
