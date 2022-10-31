package vestie.survey.domain.writtensurvey.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import lombok.RequiredArgsConstructor;
import vestie.survey.domain.writtensurvey.entity.WrittenSurvey;
import vestie.survey.domain.writtensurvey.exception.DuplicateSurveyParticipationException;
import vestie.survey.domain.writtensurvey.repository.WrittenSurveyRepository;
import vestie.survey.domain.writtensurvey.service.dto.WrittenSurveyDto;

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

		// 중복 작성 여부 확인
		checkDuplicatedAnswer(writtenSurvey.getSurveyId(), writtenSurvey.getMemberId());

		// 저장 후 반환
		return transaction.execute(status ->
			writtenSurveyRepository.save(writtenSurvey).getId()
		);
	}

	/**
	 * 채워져야 할 모든 필드가 채워졌는지 확인
	 */
	private void validateAllFieldIsSetting(WrittenSurvey writtenSurvey) {
		writtenSurvey.checkFieldNotNull();
	}

	/**
	 * 대상 설문 ID와, 회원 ID를 통해 이미 작성된 답변이 있는지 확인 후,
	 * 있다면 예외를 발생시킨다.
	 */
	private void checkDuplicatedAnswer(Long surveyId, Long memberId) {

		// 설문 ID와 회원 ID로 작성된 설문지 조회
		writtenSurveyRepository.findBySurveyIdAndMemberId(surveyId, memberId)
			.ifPresent((i) -> {
				// 있는 경우 예외 발생
				throw new DuplicateSurveyParticipationException();
			});
	}
}
