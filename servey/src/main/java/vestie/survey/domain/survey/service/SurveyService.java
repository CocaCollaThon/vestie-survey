package vestie.survey.domain.survey.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import vestie.survey.domain.survey.entity.Survey;
import vestie.survey.domain.survey.repository.SurveyRepository;
import vestie.survey.domain.survey.service.dto.SurveyDto;
import vestie.survey.domain.writtensurvey.entity.WrittenSurvey;

@Service
@RequiredArgsConstructor
public class SurveyService {

    private final SurveyRepository surveyRepository;

    private final TransactionTemplate transaction;

    public Long save(SurveyDto surveyDto) {

        // 엔티티 매핑
        Survey survey = surveyDto.toEntity();

        // 필드 잘 채워졌는지 검증
        validateAllFieldIsSetting(survey);
        return transaction.execute(status ->
                surveyRepository.save(survey).getId());
    }

    /**
     * 채워져야 할 모든 필드가 채워졌는지 확인
     */
    private void validateAllFieldIsSetting(Survey survey) {
        survey.checkFieldNotNull();
    }
}
