package vestie.survey.domain.survey.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vestie.survey.domain.answer.choice.ChoiceQuestionAnswer;
import vestie.survey.domain.answer.subjective.SubjectiveQuestionAnswer;
import vestie.survey.domain.survey.controller.response.ClosedSurveySimpleResponse;
import vestie.survey.domain.survey.controller.response.surveyResult.*;
import vestie.survey.domain.survey.entity.Survey;
import vestie.survey.domain.survey.exception.NotFoundSurveyException;
import vestie.survey.domain.survey.repository.SurveyRepository;
import vestie.survey.domain.surveyquestion.choice.ChoiceQuestion;
import vestie.survey.domain.surveyquestion.subjective.SubjectiveQuestion;
import vestie.survey.domain.writtensurvey.entity.WrittenSurvey;
import vestie.survey.domain.writtensurvey.repository.WrittenSurveyRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SurveyResultService {

    private final SurveyRepository surveyRepository;
    private final WrittenSurveyRepository writtenSurveyRepository;

    public List<ClosedSurveySimpleResponse> getAllClosedSurveyInfo() {
        // 현재 날짜 기준 종료된 설문 조회
        List<Survey> surveys = surveyRepository.findByEndDateLessThan(LocalDate.now());
        System.out.println(LocalDate.now());
        List<ClosedSurveySimpleResponse> closedSurveyList = surveys.stream().map((survey) -> {
            // 설문 참여자 수 조회
            Long participants = writtenSurveyRepository.countBySurveyId(survey.getId());
            return new ClosedSurveySimpleResponse(survey, participants.intValue());
        }).collect(Collectors.toList());
        return closedSurveyList;
    }

    public SurveyResultResponse getResultById(Long surveyId) {

        // 작성된 설문지
        List<WrittenSurvey> writtenSurveyList = writtenSurveyRepository.findAllBySurveyId(surveyId);

        // 설문 결과 response 구조 생성
        var surveyQuestionResultResponses = createSurveyQuestionResultResponseStructure(surveyId);
        SurveyResultResponse surveyResultResponse = new SurveyResultResponse(writtenSurveyList.size(), surveyQuestionResultResponses);

        // 결과 집계
        writtenSurveyList.forEach(
                (writtenSurvey) -> writtenSurvey.getAnswers().forEach(
                        (answer) -> {
                            if(answer instanceof ChoiceQuestionAnswer choiceQuestionAnswer){
                                ChoiceQuestionResultResponse choiceQuestion = surveyResultResponse.findByChoiceQuestionId(choiceQuestionAnswer.getSurveyQuestionId());
                                choiceQuestionAnswer.getSelectedChoiceOptions().forEach(
                                        (selectedChoiceOption) -> choiceQuestion.findOptionById(selectedChoiceOption.getChoiceOptionId()).countUp()
                                );
                            }
                            else if(answer instanceof SubjectiveQuestionAnswer subjectiveQuestionAnswer){
                                SubjectiveQuestionResultResponse subjectiveQuestion = surveyResultResponse.findBySubjectiveQuestionId(subjectiveQuestionAnswer.getSurveyQuestionId());
                                subjectiveQuestion.getAnswers().add(subjectiveQuestionAnswer.getAnswer());
                            }
                        }
                )
        );

        return surveyResultResponse;
    }

    /*
     * 설문 질문 목록에 따라 설문 질문 구조 초기화하는 함수
     */
    private List<SurveyQuestionResultResponse> createSurveyQuestionResultResponseStructure(Long surveyId) {
        // 질문 목록 초기화
        List<SurveyQuestionResultResponse> surveyQuestionResultResponses = new ArrayList<>();

        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new NotFoundSurveyException());

        survey.getSurveyQuestions().forEach((question) -> {
            if( question instanceof SubjectiveQuestion subjectiveQuestion) {
                surveyQuestionResultResponses.add(
                        new SubjectiveQuestionResultResponse(
                                subjectiveQuestion.getId(), subjectiveQuestion.getQuestionOrder()
                        )
                );
            }
            else if(question instanceof ChoiceQuestion choiceQuestion) {
                surveyQuestionResultResponses.add(
                        new ChoiceQuestionResultResponse(
                                choiceQuestion.getId(),
                                choiceQuestion.getQuestionOrder(),
                                choiceQuestion.getChoiceOptions().stream().map(
                                        (choiceOption) -> new SelectedChoiceOptionCountResponse(choiceOption.getId())
                                ).collect(Collectors.toList())
                        )
                );
            }
        });

        return surveyQuestionResultResponses;
    }
}
