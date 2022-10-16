package vestie.survey.domain.survey.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import vestie.survey.domain.survey.entity.Survey;
import vestie.survey.domain.survey.repository.SurveyRepository;
import vestie.survey.domain.survey.service.dto.SurveyDto;

@Service
@RequiredArgsConstructor
public class SurveyService {

    private final SurveyRepository surveyRepository;

    private final TransactionTemplate transaction;

    public Long save(SurveyDto surveyDto) {

        // 엔티티 매핑
       Survey survey = surveyDto.toEntity();

       // 필드 잘 채워졌는지 검증
//        validatedAllFieldIsSetting();

        return transaction.execute(status ->
                surveyRepository.save(survey).getId());
    }
}
