package vestie.survey.domain.writtensurvey.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import vestie.survey.domain.writtensurvey.entity.WrittenSurvey;

/**
 * Created by ShinD on 2022/10/13.
 */
public interface WrittenSurveyRepository extends JpaRepository<WrittenSurvey, Long> {

	/**
	 * 설문 Id와 회원 Id를 가지고, 회원이 작성한 설문지 찾아오는 메서드
	 * @param surveyId 설문 ID
	 * @param memberId 회원 ID
	 */
	 Optional<WrittenSurvey> findBySurveyIdAndMemberId(Long surveyId, Long memberId);

	/**
	 * 설문 Id를 가지고 작성된 모든 설문지 찾아오는 메서드
	 * @param surveyId
	 * @return
	 */
	 List<WrittenSurvey> findAllBySurveyId(Long surveyId);

	/**
	 * 설문 Id를 가지고 작성된 설문지 개수를 반환하는 메서드
	 * @param id
	 */
    Long countBySurveyId(Long id);
}
