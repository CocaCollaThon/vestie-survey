package vestie.survey.domain.survey.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vestie.survey.domain.survey.controller.response.ClosedSurveySimpleResponse;
import vestie.survey.domain.survey.controller.response.surveyQuery.ChoiceQuestionResponse;
import vestie.survey.domain.survey.controller.response.surveyQuery.SubjectiveQuestionResponse;
import vestie.survey.domain.survey.controller.response.surveyQuery.SurveyCompleteInfoResponse;
import vestie.survey.domain.survey.controller.response.surveyQuery.SurveySimpleResponse;
import vestie.survey.domain.survey.entity.Survey;
import vestie.survey.domain.survey.exception.NotFoundSurveyException;
import vestie.survey.domain.survey.repository.SurveyRepository;
import vestie.survey.domain.surveyquestion.choice.ChoiceQuestion;
import vestie.survey.domain.surveyquestion.subjective.SubjectiveQuestion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SurveyQueryService {

    private final SurveyRepository surveyRepository;

    public List<SurveySimpleResponse> getAllSimpleInfo() {
        List<Survey> surveys = surveyRepository.findAll();
        return surveys.stream().map(SurveySimpleResponse::new).toList();
    }

    public SurveyCompleteInfoResponse getSurveyCompleteInfoById(Long id) {
        Survey survey = surveyRepository.findById(id)
                .orElseThrow(NotFoundSurveyException::new);

        List<ChoiceQuestionResponse> choiceQResponses = new ArrayList<>();
        List<SubjectiveQuestionResponse> subjectiveQResponses = new ArrayList<>();

        survey.getSurveyQuestions().forEach(q -> {
            if (q instanceof SubjectiveQuestion sq) {
                subjectiveQResponses.add(new SubjectiveQuestionResponse(sq));
            }
            if (q instanceof ChoiceQuestion cq) {
                choiceQResponses.add(new ChoiceQuestionResponse(cq));
            }
        });

        return new SurveyCompleteInfoResponse(survey, choiceQResponses, subjectiveQResponses);
    }
}
