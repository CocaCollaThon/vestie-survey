package vestie.servey.domain.writtensurvey.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import lombok.RequiredArgsConstructor;
import vestie.servey.domain.writtensurvey.entity.WrittenSurvey;
import vestie.servey.domain.writtensurvey.repository.WrittenSurveyRepository;
import vestie.servey.domain.writtensurvey.service.dto.WrittenSurveyDto;

/**
 * Created by ShinD on 2022/10/13.
 */
@Service
@RequiredArgsConstructor
public class WrittenSurveyService {

	private final WrittenSurveyRepository writtenSurveyRepository;
	private final TransactionTemplate transaction;

	/**
	 * 작성된 설문지 저장
	 * @param writtenSurveyDto
	 * @return 저장된 설문 답변 Id
	 */
	public Long save(WrittenSurveyDto writtenSurveyDto) {

		// 엔티티 매핑
		WrittenSurvey writtenSurvey = writtenSurveyDto.toEntity();

		// 필드 잘 채워졌는지 검증
		validateAllFieldIsSetting(writtenSurvey);

		// 저장 후 반환
		return transaction.execute( status ->
			writtenSurveyRepository.save(writtenSurvey).getId()
		);
	}

	/**
	 * 채워져야 할 모든 필드가 채워졌는지 확인
	 */
	private void validateAllFieldIsSetting(WrittenSurvey writtenSurvey) {
		writtenSurvey.checkFieldNotNull();
	}
}
